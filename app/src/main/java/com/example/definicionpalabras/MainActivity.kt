package com.example.definicionpalabras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppUI() }
    }
}

@Composable
fun AppUI(vm: DefinitionViewModel = viewModel()) {
    var word by remember { mutableStateOf("") }
    val result by vm.definition.collectAsState()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
        ) {
            Text("Definidor de Palabras", style = MaterialTheme.typography.titleLarge)

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = word,
                onValueChange = { word = it },
                label = { Text("Escribe una palabra") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = { vm.define(word) },
                enabled = word.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Definir")
            }

            Spacer(Modifier.height(30.dp))

            Text(result, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
