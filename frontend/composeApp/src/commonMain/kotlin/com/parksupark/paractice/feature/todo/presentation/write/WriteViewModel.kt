package com.parksupark.paractice.feature.todo.presentation.write

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WriteViewModel : ViewModel() {
    private val _stateFlow: MutableStateFlow<WriteState> = MutableStateFlow(WriteState())
    val stateFlow: StateFlow<WriteState> = _stateFlow.asStateFlow()
}
