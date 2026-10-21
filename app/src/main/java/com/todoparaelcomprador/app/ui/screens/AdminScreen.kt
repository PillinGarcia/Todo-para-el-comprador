package com.todoparaelcomprador.app.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.google.firebase.firestore.FirebaseFirestore
import com.todoparaelcomprador.app.data.CloudinaryUploader
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(onBack: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var categoriaId by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    
    // Variables para la imagen
    var imagenUri by remember { mutableStateOf<Uri?>(null) }
    var imagenUrlSubida by remember { mutableStateOf<String?>(null) }

    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Lanzador para abrir la galería
    val launcherGaleria = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            imagenUri = uri
            imagenUrlSubida = null // Reiniciar si elige otra
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Panel de Administrador") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("Añadir Nuevo Producto", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))

            // Selector de imagen
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                if (imagenUri != null) {
                    AsyncImage(
                        model = imagenUri,
                        contentDescription = "Imagen seleccionada",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    OutlinedButton(
                        onClick = { launcherGaleria.launch("image/*") },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(Icons.Default.Image, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Seleccionar Imagen de Galería")
                    }
                }
            }
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre del producto") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio (ej. 25.99)") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = categoriaId, onValueChange = { categoriaId = it }, label = { Text("ID de Categoría (1 al 9)") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth())
            
            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    val precioDouble = precio.toDoubleOrNull()
                    val catInt = categoriaId.toIntOrNull()
                    if (nombre.isBlank() || precioDouble == null || catInt == null) {
                        mensaje = "Por favor, llena los campos correctamente."
                        return@Button
                    }
                    
                    isLoading = true
                    mensaje = "Subiendo imagen y guardando producto..."
                    
                    scope.launch {
                        // 1. Subir la imagen a Cloudinary (si se seleccionó una)
                        var urlFinal = ""
                        if (imagenUri != null) {
                            val inputStream = context.contentResolver.openInputStream(imagenUri!!)
                            val bytes = inputStream?.readBytes()
                            inputStream?.close()
                            
                            if (bytes != null) {
                                val url = CloudinaryUploader.subirImagen(bytes)
                                if (url != null) {
                                    urlFinal = url
                                } else {
                                    isLoading = false
                                    mensaje = "Error al subir la imagen a la nube."
                                    return@launch
                                }
                            }
                        }

                        // 2. Guardar el producto en Firebase
                        val nuevoProducto = hashMapOf(
                            "id" to (1000..9999).random(),
                            "nombre" to nombre,
                            "precio" to precioDouble,
                            "categoriaId" to catInt,
                            "descripcion" to descripcion,
                            "emoji" to "📦",
                            "imagenUrl" to urlFinal // <-- Guardamos la URL de la imagen
                        )

                        db.collection("productos").add(nuevoProducto)
                            .addOnSuccessListener {
                                mensaje = "¡Producto añadido con éxito!"
                                nombre = ""; precio = ""; categoriaId = ""; descripcion = ""
                                imagenUri = null
                                isLoading = false
                            }
                            .addOnFailureListener { e ->
                                mensaje = "Error: ${e.message}"
                                isLoading = false
                            }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Guardar Producto")
                }
            }
            
            if (mensaje.isNotEmpty()) {
                Spacer(Modifier.height(16.dp))
                Text(mensaje, color = MaterialTheme.colorScheme.primary, textAlign = androidx.compose.ui.text.style.TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}
