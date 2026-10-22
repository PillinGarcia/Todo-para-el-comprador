package com.todoparaelcomprador.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.todoparaelcomprador.app.data.DatosApp
import com.todoparaelcomprador.app.data.Producto
import com.todoparaelcomprador.app.ui.screens.AdminScreen
import com.todoparaelcomprador.app.ui.screens.CarritoScreen
import com.todoparaelcomprador.app.ui.screens.DetalleProductoScreen
import com.todoparaelcomprador.app.ui.screens.InicioScreen
import com.todoparaelcomprador.app.ui.screens.ProductosScreen
import com.todoparaelcomprador.app.viewmodel.ProductoViewModel

@Composable
fun AppNavigation(
    viewModel: ProductoViewModel
) {
    val navController = rememberNavController()
    val productos by viewModel.productos.collectAsState()

    // Carrito local en memoria (puedes migrarlo al ViewModel cuando quieras)
    var carrito by remember { mutableStateOf<List<Producto>>(emptyList()) }

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        // Pantalla de Inicio
        composable("inicio") {
            InicioScreen(
                viewModel = viewModel,
                onProductoClick = { producto ->
                    navController.navigate("detalle/${producto.id}")
                },
                onCategoriaClick = { categoriaId ->
                    navController.navigate("productos/$categoriaId")
                },
                onCarritoClick = {
                    navController.navigate("carrito")
                },
                onAdminClick = {
                    navController.navigate("admin")
                }
            )
        }

        // Pantalla de Productos filtrados por categoría
        composable(
            route = "productos/{categoriaId}",
            arguments = listOf(navArgument("categoriaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoriaId = backStackEntry.arguments?.getInt("categoriaId") ?: 1
            ProductosScreen(
                categoriaId = categoriaId,
                cantidadCarrito = carrito.size,
                onBack = { navController.popBackStack() },
                onCarritoClick = { navController.navigate("carrito") },
                onAgregar = { producto ->
                    carrito = carrito + producto
                }
            )
        }

        // Pantalla de Detalle de Producto
        composable(
            route = "detalle/{productoId}",
            arguments = listOf(navArgument("productoId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId")
            val producto = productos.find { it.id == productoId } ?: DatosApp.productos.first()

            DetalleProductoScreen(
                producto = producto,
                onVolver = { navController.popBackStack() },
                onAgregarAlCarrito = { prod ->
                    carrito = carrito + prod
                    navController.navigate("carrito")
                }
            )
        }

        // Pantalla del Carrito
        composable("carrito") {
            CarritoScreen(
                carrito = carrito,
                onBack = { navController.popBackStack() },
                onEliminar = { producto ->
                    carrito = carrito - producto
                }
            )
        }

        // Pantalla de Administración
        composable("admin") {
            AdminScreen(
                viewModel = viewModel
            )
        }
    }
}
