package com.example.definicionpalabras


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TempViewModel : ViewModel() {
    fun launch(block: suspend () -> Unit) {
        viewModelScope.launch { block() }
    }
}
