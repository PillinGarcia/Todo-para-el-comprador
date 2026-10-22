package com.todoparaelcomprador.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.todoparaelcomprador.app.data.DatosApp
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

        // Pantalla de Productos filtrados por categoría (o general)
        composable(
            route = "productos/{categoriaId}",
            arguments = listOf(navArgument("categoriaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoriaId = backStackEntry.arguments?.getInt("categoriaId") ?: 1
            ProductosScreen(
                categoriaId = categoriaId,
                viewModel = viewModel,
                onProductoClick = { producto ->
                    navController.navigate("detalle/${producto.id}")
                },
                onBack = { navController.popBackStack() },
                onCarritoClick = {
                    navController.navigate("carrito")
                }
            )
        }

        // Pantalla de Detalle de Producto estilo Mercado Libre
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
                    navController.navigate("carrito")
                }
            )
        }

        // Pantalla del Carrito de Compras
        composable("carrito") {
            CarritoScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // Pantalla de Administración (CRUD de productos)
        composable("admin") {
            AdminScreen(
                viewModel = viewModel
            )
        }
    }
}
