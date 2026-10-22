package com.todoparaelcomprador.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.todoparaelcomprador.app.navigation.AppNavigation
import com.todoparaelcomprador.app.ui.theme.TodoParaElCompradorTheme
import com.todoparaelcomprador.app.viewmodel.ProductoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoParaElCompradorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val viewModel: ProductoViewModel = viewModel()
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}
