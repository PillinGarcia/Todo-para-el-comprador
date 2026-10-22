package com.todoparaelcomprador.app.viewmodel

import androidx.lifecycle.ViewModel
import com.todoparaelcomprador.app.data.DatosApp
import com.todoparaelcomprador.app.data.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductoViewModel : ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(DatosApp.productos)
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    // NUEVO: estado de carga requerido por InicioScreen
    private val _cargando = MutableStateFlow(false)
    val cargando: StateFlow<Boolean> = _cargando.asStateFlow()

    fun agregarProducto(nuevoProducto: Producto) {
        _productos.value = _productos.value + nuevoProducto
    }

    fun actualizarProducto(productoActualizado: Producto) {
        _productos.value = _productos.value.map {
            if (it.id == productoActualizado.id) productoActualizado else it
        }
    }

    fun eliminarProducto(id: String) {
        _productos.value = _productos.value.filter { it.id != id }
    }
}
