package com.example.definicionpalabras

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.definicionpalabras.api.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*

class DefinitionViewModel : ViewModel() {

    private val _definition = MutableStateFlow("")
    val definition = _definition.asStateFlow()

    fun define(word: String) {
        viewModelScope.launch {
            try {
                val response = OpenAIClient.api.ask(
                    ChatRequest(
                        model = "gpt-4o-mini",
                        messages = listOf(
                            Message("user", "Define la palabra: $word")
                        )
                    )
                )

                _definition.value =
                    response.choices.firstOrNull()?.message?.content ?: "No se encontró definición."

            } catch (e: Exception) {
                _definition.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}


