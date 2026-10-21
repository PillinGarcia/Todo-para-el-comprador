package com.todoparaelcomprador.app.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.todoparaelcomprador.app.data.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductoViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    
    // Estado que guarda la lista de productos
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    // Estado para saber si está cargando
    private val _cargando = MutableStateFlow(true)
    val cargando: StateFlow<Boolean> = _cargando

    init {
        escucharProductos()
    }

    private fun escucharProductos() {
        db.collection("productos")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    _cargando.value = false
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val lista = snapshot.documents.mapNotNull { doc ->
                        try {
                            Producto(
                                id = (doc.getLong("id") ?: 0).toInt(), // Si usas UUID, cámbialo a String
                                nombre = doc.getString("nombre") ?: "",
                                precio = doc.getDouble("precio") ?: 0.0,
                                categoriaId = (doc.getLong("categoriaId") ?: 0).toInt(),
                                descripcion = doc.getString("descripcion") ?: "",
                                emoji = doc.getString("emoji") ?: "📦"
                                imagenUrl = doc.getString("imagenUrl") ?: ""
                            )
                        } catch (e: Exception) {
                            null
                        }
                    }
                    _productos.value = lista
                    _cargando.value = false
                }
            }
    }
}
