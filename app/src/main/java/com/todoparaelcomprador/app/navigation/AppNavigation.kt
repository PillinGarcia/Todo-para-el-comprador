package com.todoparaelcomprador.app.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.todoparaelcomprador.app.data.Producto
import com.todoparaelcomprador.app.ui.screens.CarritoScreen
import com.todoparaelcomprador.app.ui.screens.InicioScreen
import com.todoparaelcomprador.app.ui.screens.ProductosScreen

object Rutas {
    const val INICIO     = "inicio"
    const val PRODUCTOS  = "productos"
    const val CARRITO    = "carrito"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val carrito = remember { mutableStateListOf<Producto>() }

    NavHost(
        navController = navController,
        startDestination = Rutas.INICIO
    ) {
        composable(Rutas.INICIO) {
            InicioScreen(
                cantidadCarrito = carrito.size,
                onCategoriaClick = { id ->
                    navController.navigate("${Rutas.PRODUCTOS}/$id")
                },
                onCarritoClick = {
                    navController.navigate(Rutas.CARRITO)
                }
            )
        }

        composable(
            route = "${Rutas.PRODUCTOS}/{categoriaId}",
            arguments = listOf(navArgument("categoriaId") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("categoriaId") ?: 0
            ProductosScreen(
                categoriaId = id,
                cantidadCarrito = carrito.size,
                onBack = { navController.popBackStack() },
                onCarritoClick = { navController.navigate(Rutas.CARRITO) },
                onAgregar = { carrito.add(it) }
            )
        }

        composable(Rutas.CARRITO) {
            CarritoScreen(
                carrito = carrito,
                onBack = { navController.popBackStack() },
                onEliminar = { carrito.remove(it) }
            )
        }
    }
}