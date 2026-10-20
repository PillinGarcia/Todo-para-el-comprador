package com.todoparaelcomprador.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(onBack: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var categoriaId by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val db = FirebaseFirestore.getInstance()

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
            Text("Añadir Nuevo Producto", fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            Spacer(Modifier.height(16.dp))

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
                    val nuevoProducto = hashMapOf(
                        "id" to (1000..9999).random(),
                        "nombre" to nombre,
                        "precio" to precioDouble,
                        "categoriaId" to catInt,
                        "descripcion" to descripcion,
                        "emoji" to "📦" // Emoji por defecto
                    )

                    db.collection("productos").add(nuevoProducto)
                        .addOnSuccessListener {
                            mensaje = "¡Producto añadido con éxito!"
                            nombre = ""; precio = ""; categoriaId = ""; descripcion = ""
                            isLoading = false
                        }
                        .addOnFailureListener { e ->
                            mensaje = "Error: ${e.message}"
                            isLoading = false
                        }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Guardar Producto en la Nube")
                }
            }
            
            if (mensaje.isNotEmpty()) {
                Spacer(Modifier.height(16.dp))
                Text(mensaje, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
