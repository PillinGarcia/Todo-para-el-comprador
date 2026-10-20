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
    const val AUTH        = "auth"
    const val INICIO      = "inicio"
    const val PRODUCTOS   = "productos"
    const val CARRITO     = "carrito"
    const val ADMIN       = "admin" // Nueva ruta para el administrador
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val carrito = remember { mutableStateListOf<Producto>() }
    var isAdmin by remember { mutableStateOf(false) }


    NavHost(
        navController = navController,
        startDestination = Rutas.AUTH // <-- Iniciamos en el Login
    ) {
        composable(Rutas.AUTH) {
            AuthScreen(
                onLoginSuccess = { admin ->
                    isAdmin = admin
                    navController.navigate(Rutas.INICIO) {
                        popUpTo(Rutas.AUTH) { inclusive = true } // Evita volver al login con "atrás"
                    }
                }
            )
        }

        composable(Rutas.INICIO) {
            InicioScreen(
                cantidadCarrito = carrito.size,
                isAdmin = isAdmin,
                onCategoriaClick = { id -> navController.navigate("${Rutas.PRODUCTOS}/$id") },
                onCarritoClick = { navController.navigate(Rutas.CARRITO) },
                onAgregar = { carrito.add(it) },
                onAdminClick = { navController.navigate(Rutas.ADMIN) } // Botón para ir al panel
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

        composable(Rutas.ADMIN) {
            AdminScreen(onBack = { navController.popBackStack() })
        }
    }
}
