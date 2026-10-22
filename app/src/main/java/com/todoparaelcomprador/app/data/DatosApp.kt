package com.todoparaelcomprador.app.data

object DatosApp {

    val categorias = listOf(
        Categoria(1, "Electrónica",         "📱", 0xFF2196F3),
        Categoria(2, "Calzado",               "👟", 0xFF9C27B0),
        Categoria(3, "Ropa y Textiles",       "👕", 0xFFE91E63),
        Categoria(4, "Juguetes",              "🧸", 0xFFFF9800),
        Categoria(5, "Cumpleaños",            "🎂", 0xFFF44336),
        Categoria(6, "Papelería",             "✏️", 0xFF4CAF50),
        Categoria(7, "Artículos del Hogar",   "🏠", 0xFF795548),
        Categoria(8, "Higiene Personal",      "🧼", 0xFF00BCD4),
        Categoria(9, "Ferretería",            "🔧", 0xFF607D8B)
    )

    val productos = listOf(
        // ELECTRÓNICA
        Producto(
            id = "1",
            nombre = "Audífonos Bluetooth",
            descripcion = "Sonido HD con cancelación de ruido",
            precio = 25.99,
            imagenUrl = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "1",
            stock = 10,
            visible = true
        ),
        Producto(
            id = "2",
            nombre = "Cargador rápido USB-C",
            descripcion = "Carga completa en 30 minutos",
            precio = 12.50,
            imagenUrl = "https://images.unsplash.com/photo-1583863788434-e58a36330cf0?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "1",
            stock = 15,
            visible = true
        ),
        Producto(
            id = "3",
            nombre = "Power Bank 10000mAh",
            descripcion = "Batería portátil de alta capacidad",
            precio = 19.99,
            imagenUrl = "https://images.unsplash.com/photo-1609592424104-12d22228a74e?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "1",
            stock = 8,
            visible = true
        ),
        Producto(
            id = "4",
            nombre = "Smartwatch deportivo",
            descripcion = "Monitor ritmo cardíaco + GPS",
            precio = 45.00,
            imagenUrl = "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "1",
            stock = 5,
            visible = true
        ),
        Producto(
            id = "5",
            nombre = "Parlante Bluetooth",
            descripcion = "Resistente al agua IPX7",
            precio = 32.00,
            imagenUrl = "https://images.unsplash.com/photo-1545454675-3531b543be5d?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "1",
            stock = 7,
            visible = true
        ),

        // CALZADO
        Producto(
            id = "6",
            nombre = "Zapatillas deportivas",
            descripcion = "Comodidad para correr",
            precio = 55.00,
            imagenUrl = "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "2",
            stock = 12,
            visible = true
        ),
        Producto(
            id = "7",
            nombre = "Sandalias de verano",
            descripcion = "Ligeras y frescas",
            precio = 22.00,
            imagenUrl = "https://images.unsplash.com/photo-1603808033192-082d6919d3e1?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "2",
            stock = 20,
            visible = true
        ),
        Producto(
            id = "8",
            nombre = "Botas casuales",
            descripcion = "Estilo urbano resistente",
            precio = 65.00,
            imagenUrl = "https://images.unsplash.com/photo-1520639888713-7851133b1ed0?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "2",
            stock = 4,
            visible = true
        ),
        Producto(
            id = "9",
            nombre = "Zapatos formales",
            descripcion = "Cuero genuino elegante",
            precio = 70.00,
            imagenUrl = "https://images.unsplash.com/photo-1614252235316-8c857d38b5f4?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "2",
            stock = 6,
            visible = true
        ),

        // ROPA
        Producto(
            id = "10",
            nombre = "Camiseta algodón",
            descripcion = "100% algodón suave",
            precio = 15.00,
            imagenUrl = "https://images.unsplash.com/photo-1521572267360-ee0c2909d518?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "3",
            stock = 25,
            visible = true
        ),
        Producto(
            id = "11",
            nombre = "Jeans clásicos",
            descripcion = "Corte recto duradero",
            precio = 35.00,
            imagenUrl = "https://images.unsplash.com/photo-1542272604-787c3835535d?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "3",
            stock = 14,
            visible = true
        ),
        Producto(
            id = "12",
            nombre = "Chaqueta impermeable",
            descripcion = "Ideal para lluvia",
            precio = 60.00,
            imagenUrl = "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "3",
            stock = 8,
            visible = true
        ),
        Producto(
            id = "13",
            nombre = "Vestido casual",
            descripcion = "Diseño moderno y fresco",
            precio = 42.00,
            imagenUrl = "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "3",
            stock = 10,
            visible = true
        ),

        // JUGUETES
        Producto(
            id = "14",
            nombre = "Bloques de construcción",
            descripcion = "200 piezas didácticas",
            precio = 28.00,
            imagenUrl = "https://images.unsplash.com/photo-1587654780291-39c9404d746b?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "4",
            stock = 11,
            visible = true
        ),
        Producto(
            id = "15",
            nombre = "Muñeca interactiva",
            descripcion = "Habla y canta",
            precio = 32.00,
            imagenUrl = "https://images.unsplash.com/photo-1563245372-f21724e3856d?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "4",
            stock = 9,
            visible = true
        ),
        Producto(
            id = "16",
            nombre = "Carro a control remoto",
            descripcion = "Alta velocidad todoterreno",
            precio = 40.00,
            imagenUrl = "https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "4",
            stock = 5,
            visible = true
        ),
        Producto(
            id = "17",
            nombre = "Rompecabezas 500 piezas",
            descripcion = "Paisaje para adultos",
            precio = 15.00,
            imagenUrl = "https://images.unsplash.com/photo-1611996575749-79a3a250f948?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "4",
            stock = 15,
            visible = true
        ),

        // CUMPLEAÑOS
        Producto(
            id = "18",
            nombre = "Globos decorativos x50",
            descripcion = "Colores surtidos",
            precio = 8.00,
            imagenUrl = "https://images.unsplash.com/photo-1530103862676-de8c9debad1d?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "5",
            stock = 30,
            visible = true
        ),
        Producto(
            id = "19",
            nombre = "Pastel de chocolate",
            descripcion = "Para 10 personas",
            precio = 25.00,
            imagenUrl = "https://images.unsplash.com/photo-1578985545062-69928b1d9587?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "5",
            stock = 3,
            visible = true
        ),
        Producto(
            id = "20",
            nombre = "Set velas y gorros",
            descripcion = "Fiesta completa",
            precio = 6.50,
            imagenUrl = "https://images.unsplash.com/photo-1513151233558-d860c5398176?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "5",
            stock = 25,
            visible = true
        ),
        Producto(
            id = "21",
            nombre = "Piñata temática",
            descripcion = "Diseño infantil personalizable",
            precio = 18.00,
            imagenUrl = "https://images.unsplash.com/photo-1512909006721-3d6018887383?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "5",
            stock = 5,
            visible = true
        ),

        // PAPELERÍA
        Producto(
            id = "22",
            nombre = "Cuaderno universitario",
            descripcion = "100 hojas rayadas",
            precio = 3.50,
            imagenUrl = "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "6",
            stock = 40,
            visible = true
        ),
        Producto(
            id = "23",
            nombre = "Set de bolígrafos x12",
            descripcion = "Colores variados",
            precio = 7.00,
            imagenUrl = "https://images.unsplash.com/photo-1583485088034-697b5bc54ccd?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "6",
            stock = 20,
            visible = true
        ),
        Producto(
            id = "24",
            nombre = "Mochila escolar",
            descripcion = "Resistente al agua",
            precio = 30.00,
            imagenUrl = "https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "6",
            stock = 10,
            visible = true
        ),
        Producto(
            id = "25",
            nombre = "Set de colores x24",
            descripcion = "Para dibujo artístico",
            precio = 9.00,
            imagenUrl = "https://images.unsplash.com/photo-1513519245088-0e12902e5a38?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "6",
            stock = 15,
            visible = true
        ),

        // HOGAR
        Producto(
            id = "26",
            nombre = "Juego de sábanas",
            descripcion = "Queen size 4 piezas",
            precio = 45.00,
            imagenUrl = "https://images.unsplash.com/photo-1522771739844-6a9f6d5f14af?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "7",
            stock = 7,
            visible = true
        ),
        Producto(
            id = "27",
            nombre = "Set de ollas x5",
            descripcion = "Anti-adherentes premium",
            precio = 75.00,
            imagenUrl = "https://images.unsplash.com/photo-1584990347426-c785bc874f11?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "7",
            stock = 4,
            visible = true
        ),
        Producto(
            id = "28",
            nombre = "Lámpara LED de mesa",
            descripcion = "Luz regulable táctil",
            precio = 18.00,
            imagenUrl = "https://images.unsplash.com/photo-1507473885765-e6ed057f782c?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "7",
            stock = 12,
            visible = true
        ),
        Producto(
            id = "29",
            nombre = "Almohada ortopédica",
            descripcion = "Espuma viscoelástica",
            precio = 22.00,
            imagenUrl = "https://images.unsplash.com/photo-1584100936595-c0654b55a2e2?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "7",
            stock = 8,
            visible = true
        ),

        // HIGIENE
        Producto(
            id = "30",
            nombre = "Kit cepillo dental",
            descripcion = "Incluye pasta + estuche",
            precio = 5.00,
            imagenUrl = "https://images.unsplash.com/photo-1559599107-f81b67941d4e?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "8",
            stock = 25,
            visible = true
        ),
        Producto(
            id = "31",
            nombre = "Shampoo anticaída",
            descripcion = "Fórmula con biotina 500ml",
            precio = 12.00,
            imagenUrl = "https://images.unsplash.com/photo-1535585209827-a15fcdbc4c2d?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "8",
            stock = 14,
            visible = true
        ),
        Producto(
            id = "32",
            nombre = "Crema humectante",
            descripcion = "Piel suave todo el día",
            precio = 9.50,
            imagenUrl = "https://images.unsplash.com/photo-1608248597359-99435a435882?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "8",
            stock = 18,
            visible = true
        ),
        Producto(
            id = "33",
            nombre = "Gel antibacterial 1L",
            descripcion = "70% alcohol",
            precio = 6.00,
            imagenUrl = "https://images.unsplash.com/photo-1584483766114-2cea6facaf57?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "8",
            stock = 20,
            visible = true
        ),

        // FERRETERÍA
        Producto(
            id = "34",
            nombre = "Taladro inalámbrico",
            descripcion = "Con batería y maletín",
            precio = 85.00,
            imagenUrl = "https://images.unsplash.com/photo-1504148455328-c376907d081c?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "9",
            stock = 3,
            visible = true
        ),
        Producto(
            id = "35",
            nombre = "Caja herramientas 60pz",
            descripcion = "Acero cromo vanadio",
            precio = 50.00,
            imagenUrl = "https://images.unsplash.com/photo-1530124566582-a618bc2615dc?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "9",
            stock = 5,
            visible = true
        ),
        Producto(
            id = "36",
            nombre = "Martillo profesional",
            descripcion = "Mango antideslizante",
            precio = 15.00,
            imagenUrl = "https://images.unsplash.com/photo-1586864387967-d02ef85d93e8?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "9",
            stock = 10,
            visible = true
        ),
        Producto(
            id = "37",
            nombre = "Set destornilladores",
            descripcion = "12 piezas precisión",
            precio = 20.00,
            imagenUrl = "https://images.unsplash.com/photo-1540339832862-474599807836?w=500",
            imagenesSecundarias = emptyList(),
            categoria = "9",
            stock = 12,
            visible = true
        )
    )

    fun productosPorCategoria(categoriaId: Int): List<Producto> =
        productos.filter { it.categoria.toIntOrNull() == categoriaId }

    fun categoriaPorId(id: Int): Categoria? =
        categorias.find { it.id == id }
}

    fun categoriaPorId(id: Int): Categoria? =
        categorias.find { it.id == id }
}
