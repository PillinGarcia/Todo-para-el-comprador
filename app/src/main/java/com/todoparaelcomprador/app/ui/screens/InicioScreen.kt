package com.todoparaelcomprador.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.todoparaelcomprador.app.R
import com.todoparaelcomprador.app.data.DatosApp
import com.todoparaelcomprador.app.data.Producto
import com.todoparaelcomprador.app.ui.theme.AmarilloOferta
import com.todoparaelcomprador.app.ui.theme.VerdeExito
import com.todoparaelcomprador.app.viewmodel.ProductoViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    viewModel: ProductoViewModel,
    onProductoClick: (Producto) -> Unit,
    onCategoriaClick: (Int) -> Unit,
    onCarritoClick: () -> Unit,
    onAdminClick: () -> Unit
) {
    val productos by viewModel.productos.collectAsState()
    val cargando by viewModel.cargando.collectAsState()

    // Valores provisionales (conéctalos a tu lógica real de carrito/admin cuando la tengas)
    val cantidadCarrito = 0
    val isAdmin = true
    val onAgregar: (Producto) -> Unit = { producto -> onProductoClick(producto) }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .statusBarsPadding()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // 1. Logo
                    Image(
                        painter = painterResource(id = R.drawable.logo_tienda),
                        contentDescription = "Logo",
                        modifier = Modifier.size(40.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.width(12.dp))

                    // 2. Barra de búsqueda
                    Surface(
                        modifier = Modifier.weight(1f).height(40.dp),
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        ) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Buscar",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "Buscar en TODO PARA EL COMPRADOR",
                                color = Color.Gray,
                                fontSize = 13.sp,
                                maxLines = 1
                            )
                        }
                    }
                    Spacer(Modifier.width(8.dp))

                    // 3. Botón de administrador
                    if (isAdmin) {
                        IconButton(onClick = onAdminClick) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Admin",
                                tint = Color.White
                            )
                        }
                    }

                    // 4. Carrito
                    BadgedBox(
                        badge = {
                            if (cantidadCarrito > 0) {
                                Badge(containerColor = MaterialTheme.colorScheme.secondary) {
                                    Text("$cantidadCarrito", color = Color.White)
                                }
                            }
                        }
                    ) {
                        IconButton(onClick = onCarritoClick) {
                            Icon(Icons.Default.ShoppingCart, "Carrito", tint = Color.White)
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))

                // 5. Categorías horizontales
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(DatosApp.categorias) { cat ->
                        Text(
                            text = cat.nombre,
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 13.sp,
                            modifier = Modifier.clickable { onCategoriaClick(cat.id) }
                        )
                    }
                }
            }
        }
    ) { padding ->
        if (cargando) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Banner de Ofertas
                item(span = { GridItemSpan(2) }) {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    "REMATES DE TEMPORADA",
                                    color = AmarilloOferta,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                                Text(
                                    "HASTA 50% OFF",
                                    color = Color.White,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 24.sp
                                )
                                Spacer(Modifier.height(8.dp))
                                Surface(color = Color.White, shape = RoundedCornerShape(8.dp)) {
                                    Text(
                                        "Ver ofertas",
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                    )
                                }
                            }
                            Text("🛍️", fontSize = 50.sp)
                        }
                    }
                }

                item(span = { GridItemSpan(2) }) {
                    Text(
                        "Ofertas Relámpago ⚡",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // Productos dinámicos desde el ViewModel
                if (productos.isEmpty()) {
                    item(span = { GridItemSpan(2) }) {
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "No hay productos agregados aún. (Entra como Admin para agregar)",
                                color = Color.Gray
                            )
                        }
                    }
                } else {
                    items(productos) { producto ->
                        ProductoCardMercadoLibre(
                            producto = producto,
                            onAgregar = onAgregar,
                            onClick = { onProductoClick(producto) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductoCardMercadoLibre(
    producto: Producto,
    onAgregar: (Producto) -> Unit,
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            // Imagen o fallback con iniciales
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (producto.imagenUrl.isNotEmpty()) {
                    AsyncImage(
                        model = producto.imagenUrl,
                        contentDescription = producto.nombre,
                        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = producto.nombre.take(2).uppercase(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                // Etiqueta "Más Vendido"
                if (producto.precio > 30) {
                    Surface(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.align(Alignment.BottomStart).padding(8.dp)
                    ) {
                        Text(
                            "MÁS VENDIDO",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Text(
                producto.nombre,
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp
            )

            Spacer(Modifier.height(4.dp))

            val precioOriginal = producto.precio * 1.25
            Text(
                "$${String.format(Locale.US, "%.2f", precioOriginal)}",
                fontSize = 12.sp,
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "$${String.format(Locale.US, "%.2f", producto.precio)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    "20% OFF",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = VerdeExito
                )
            }

            Spacer(Modifier.height(4.dp))
            Text("Envío gratis", fontSize = 12.sp, color = VerdeExito, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = { onAgregar(producto) },
                modifier = Modifier.fillMaxWidth().height(36.dp),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                )
            ) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "Agregar",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    "Agregar",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
