package com.todoparaelcomprador.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.todoparaelcomprador.app.data.Producto
import com.todoparaelcomprador.app.viewmodel.ProductoViewModel

@Composable
fun AdminScreen(
    viewModel: ProductoViewModel
) {
    val productos by viewModel.productos.collectAsState()
    var mostrarDialogoCrearEditar by remember { mutableStateOf(false) }
    var productoAEditar by remember { mutableStateOf<Producto?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    productoAEditar = null
                    mostrarDialogoCrearEditar = true
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Producto", tint = Color.White)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Gestión de Productos",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(productos) { producto ->
                    ItemAdminProducto(
                        producto = producto,
                        onEditar = {
                            productoAEditar = producto
                            mostrarDialogoCrearEditar = true
                        },
                        onCambiarVisibilidad = {
                            viewModel.actualizarProducto(producto.copy(visible = !producto.visible))
                        },
                        onEliminar = {
                            viewModel.eliminarProducto(producto.id)
                        }
                    )
                }
            }
        }
    }

    if (mostrarDialogoCrearEditar) {
        DialogoProductoFormulario(
            productoInicial = productoAEditar,
            onDismiss = { mostrarDialogoCrearEditar = false },
            onGuardar = { productoGuardar ->
                if (productoAEditar == null) {
                    viewModel.agregarProducto(productoGuardar)
                } else {
                    viewModel.actualizarProducto(productoGuardar)
                }
                mostrarDialogoCrearEditar = false
            }
        )
    }
}

@Composable
fun ItemAdminProducto(
    producto: Producto,
    onEditar: () -> Unit,
    onCambiarVisibilidad: () -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(producto.nombre, fontWeight = FontWeight.Bold)
                Text("$${producto.precio}", color = MaterialTheme.colorScheme.primary)
                Text(
                    text = if (producto.visible) "Estado: Visible" else "Estado: Oculto",
                    fontSize = 12.sp,
                    color = if (producto.visible) Color(0xFF2E7D32) else Color.Red
                )
            }

            Row {
                IconButton(onClick = onCambiarVisibilidad) {
                    Icon(
                        if (producto.visible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Visibilidad"
                    )
                }
                IconButton(onClick = onEditar) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar", tint = Color.Gray)
                }
                IconButton(onClick = onEliminar) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                }
            }
        }
    }
}

@Composable
fun DialogoProductoFormulario(
    productoInicial: Producto?,
    onDismiss: () -> Unit,
    onGuardar: (Producto) -> Unit
) {
    var nombre by remember { mutableStateOf(productoInicial?.nombre ?: "") }
    var precio by remember { mutableStateOf(productoInicial?.precio?.toString() ?: "") }
    var descripcion by remember { mutableStateOf(productoInicial?.descripcion ?: "") }
    var imagenUrl by remember { mutableStateOf(productoInicial?.imagenUrl ?: "") }
    var stock by remember { mutableStateOf(productoInicial?.stock?.toString() ?: "0") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (productoInicial == null) "Agregar Producto" else "Editar Producto") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
                OutlinedTextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") })
                OutlinedTextField(value = stock, onValueChange = { stock = it }, label = { Text("Stock") })
                OutlinedTextField(value = imagenUrl, onValueChange = { imagenUrl = it }, label = { Text("URL de Imagen") })
                OutlinedTextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") })
            }
        },
        confirmButton = {
            Button(onClick = {
                val nuevoProducto = Producto(
                    id = productoInicial?.id ?: System.currentTimeMillis().toString(),
                    nombre = nombre,
                    precio = precio.toDoubleOrNull() ?: 0.0,
                    descripcion = descripcion,
                    imagenUrl = imagenUrl,
                    stock = stock.toIntOrNull() ?: 0,
                    visible = productoInicial?.visible ?: true
                )
                onGuardar(nuevoProducto)
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
