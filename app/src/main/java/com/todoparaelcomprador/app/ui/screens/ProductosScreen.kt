package com.todoparaelcomprador.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.todoparaelcomprador.app.data.DatosApp
import com.todoparaelcomprador.app.data.Producto
import com.todoparaelcomprador.app.ui.components.ProductoCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen(
    categoriaId: Int,
    cantidadCarrito: Int,
    onBack: () -> Unit,
    onCarritoClick: () -> Unit,
    onAgregar: (Producto) -> Unit
) {
    val categoria = DatosApp.categoriaPorId(categoriaId)
    val productos = DatosApp.productosPorCategoria(categoriaId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("${categoria?.emoji ?: ""} ${categoria?.nombre ?: "Productos"}",
                        fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                },
                actions = {
                    BadgedBox(
                        badge = {
                            if (cantidadCarrito > 0) {
                                Badge { Text("$cantidadCarrito") }
                            }
                        }
                    ) {
                        IconButton(onClick = onCarritoClick) {
                            Icon(Icons.Default.ShoppingCart, "Carrito")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            item {
                Text(
                    "${productos.size} productos disponibles",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
            items(productos) { prod ->
                ProductoCard(prod, onAgregar)
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}