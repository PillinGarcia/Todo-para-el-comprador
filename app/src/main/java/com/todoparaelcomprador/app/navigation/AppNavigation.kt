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
        // Pantalla de Inicio / Catálogo general
        composable("inicio") {
            InicioScreen(
                viewModel = viewModel,
                onProductoClick = { producto ->
                    navController.navigate("detalle/${producto.id}")
                },
                onIrAlCarrito = {
                    navController.navigate("carrito")
                },
                onIrAlAdmin = {
                    navController.navigate("admin")
                }
            )
        }

        // Pantalla de Productos por categoría o filtrados
        composable("productos") {
            ProductosScreen(
                viewModel = viewModel,
                onProductoClick = { producto ->
                    navController.navigate("detalle/${producto.id}")
                },
                onVolver = { navController.popBackStack() }
            )
        }

        // Pantalla de Detalle de Producto estilo Mercado Libre
        composable(
            route = "detalle/{productoId}",
            arguments = listOf(navArgument("productoId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId")
            // Buscamos el producto en la lista del ViewModel
            val producto = productos.find { it.id == productoId } ?: DatosApp.productos.first()

            DetalleProductoScreen(
                producto = producto,
                onVolver = { navController.popBackStack() },
                onAgregarAlCarrito = { prod ->
                    // Lógica para agregar al carrito si lo manejas en tu app
                    navController.navigate("carrito")
                }
            )
        }

        // Pantalla del Carrito de Compras
        composable("carrito") {
            CarritoScreen(
                onVolver = { navController.popBackStack() }
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
