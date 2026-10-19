package com.todoparaelcomprador.app.data

object DatosApp {

    val categorias = listOf(
        Categoria(1, "Electrónica",         "📱", 0xFF2196F3),
        Categoria(2, "Calzado",             "👟", 0xFF9C27B0),
        Categoria(3, "Ropa y Textiles",     "👕", 0xFFE91E63),
        Categoria(4, "Juguetes",            "🧸", 0xFFFF9800),
        Categoria(5, "Cumpleaños",          "🎂", 0xFFF44336),
        Categoria(6, "Papelería",           "✏️", 0xFF4CAF50),
        Categoria(7, "Artículos del Hogar", "🏠", 0xFF795548),
        Categoria(8, "Higiene Personal",    "🧼", 0xFF00BCD4),
        Categoria(9, "Ferretería",          "🔧", 0xFF607D8B)
    )

    val productos = listOf(
        // ELECTRÓNICA
        Producto(1,  "Audífonos Bluetooth",    25.99, 1, "Sonido HD con cancelación de ruido", "🎧"),
        Producto(2,  "Cargador rápido USB-C",  12.50, 1, "Carga completa en 30 minutos",       "🔌"),
        Producto(3,  "Power Bank 10000mAh",    19.99, 1, "Batería portátil de alta capacidad", "🔋"),
        Producto(4,  "Smartwatch deportivo",   45.00, 1, "Monitor ritmo cardíaco + GPS",       "⌚"),
        Producto(5,  "Parlante Bluetooth",     32.00, 1, "Resistente al agua IPX7",            "🔊"),

        // CALZADO
        Producto(6,  "Zapatillas deportivas",  55.00, 2, "Comodidad para correr",              "👟"),
        Producto(7,  "Sandalias de verano",    22.00, 2, "Ligeras y frescas",                  "🩴"),
        Producto(8,  "Botas casuales",         65.00, 2, "Estilo urbano resistente",           "🥾"),
        Producto(9,  "Zapatos formales",       70.00, 2, "Cuero genuino elegante",             "👞"),

        // ROPA
        Producto(10, "Camiseta algodón",       15.00, 3, "100% algodón suave",                 "👕"),
        Producto(11, "Jeans clásicos",         35.00, 3, "Corte recto duradero",               "👖"),
        Producto(12, "Chaqueta impermeable",   60.00, 3, "Ideal para lluvia",                  "🧥"),
        Producto(13, "Vestido casual",         42.00, 3, "Diseño moderno y fresco",            "👗"),

        // JUGUETES
        Producto(14, "Bloques de construcción", 28.00, 4, "200 piezas didácticas",             "🧱"),
        Producto(15, "Muñeca interactiva",      32.00, 4, "Habla y canta",                     "🪆"),
        Producto(16, "Carro a control remoto",  40.00, 4, "Alta velocidad todoterreno",        "🚗"),
        Producto(17, "Rompecabezas 500 piezas", 15.00, 4, "Paisaje para adultos",              "🧩"),

        // CUMPLEAÑOS
        Producto(18, "Globos decorativos x50",  8.00,  5, "Colores surtidos",                  "🎈"),
        Producto(19, "Pastel de chocolate",     25.00, 5, "Para 10 personas",                  "🎂"),
        Producto(20, "Set velas y gorros",      6.50,  5, "Fiesta completa",                   "🎉"),
        Producto(21, "Piñata temática",         18.00, 5, "Diseño infantil personalizable",    "🪅"),

        // PAPELERÍA
        Producto(22, "Cuaderno universitario",  3.50,  6, "100 hojas rayadas",                 "📓"),
        Producto(23, "Set de bolígrafos x12",   7.00,  6, "Colores variados",                  "🖊️"),
        Producto(24, "Mochila escolar",         30.00, 6, "Resistente al agua",                "🎒"),
        Producto(25, "Set de colores x24",      9.00,  6, "Para dibujo artístico",             "🖍️"),

        // HOGAR
        Producto(26, "Juego de sábanas",        45.00, 7, "Queen size 4 piezas",               "🛏️"),
        Producto(27, "Set de ollas x5",         75.00, 7, "Anti-adherentes premium",           "🍳"),
        Producto(28, "Lámpara LED de mesa",     18.00, 7, "Luz regulable táctil",              "💡"),
        Producto(29, "Almohada ortopédica",     22.00, 7, "Espuma viscoelástica",              "🛋️"),

        // HIGIENE
        Producto(30, "Kit cepillo dental",      5.00,  8, "Incluye pasta + estuche",           "🪥"),
        Producto(31, "Shampoo anticaída",       12.00, 8, "Fórmula con biotina 500ml",         "🧴"),
        Producto(32, "Crema humectante",        9.50,  8, "Piel suave todo el día",            "🧴"),
        Producto(33, "Gel antibacterial 1L",    6.00,  8, "70% alcohol",                       "🧼"),

        // FERRETERÍA
        Producto(34, "Taladro inalámbrico",     85.00, 9, "Con batería y maletín",             "🔩"),
        Producto(35, "Caja herramientas 60pz",  50.00, 9, "Acero cromo vanadio",               "🧰"),
        Producto(36, "Martillo profesional",    15.00, 9, "Mango antideslizante",              "🔨"),
        Producto(37, "Set destornilladores",    20.00, 9, "12 piezas precisión",               "🪛")
    )

    fun productosPorCategoria(categoriaId: Int): List<Producto> =
        productos.filter { it.categoriaId == categoriaId }

    fun categoriaPorId(id: Int): Categoria? =
        categorias.find { it.id == id }
}