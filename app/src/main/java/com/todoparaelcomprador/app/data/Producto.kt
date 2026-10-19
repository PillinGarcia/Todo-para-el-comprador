package com.todoparaelcomprador.app.data

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val categoriaId: Int,
    val descripcion: String,
    val emoji: String
)