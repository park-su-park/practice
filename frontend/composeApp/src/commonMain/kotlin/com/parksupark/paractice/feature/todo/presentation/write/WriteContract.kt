package com.parksupark.paractice.feature.todo.presentation.write

import androidx.compose.foundation.text.input.TextFieldState

data class WriteState(
    val title: TextFieldState = TextFieldState(),
    val content: TextFieldState = TextFieldState(),
)

data class WriteActions(
    val onConfirm: () -> Unit = { },
)
