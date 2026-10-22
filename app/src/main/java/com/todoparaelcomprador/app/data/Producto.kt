package com.todoparaelcomprador.app.data

data class Producto(
    val id: String = "",
    val nombre: String = "",
    val descripcion: String = "",
    val precio: Double = 0.0,
    val imagenUrl: String = "",
    val imagenesSecundarias: List<String> = emptyList(),
    val categoria: String = "",
    val stock: Int = 0,
    val visible: Boolean = true, // Para ocultar/mostrar productos
    val especificaciones: Map<String, String> = emptyMap()
)
