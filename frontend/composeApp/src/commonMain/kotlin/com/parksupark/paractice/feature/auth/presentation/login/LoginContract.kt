package com.parksupark.paractice.feature.auth.presentation.login

import androidx.compose.foundation.text.input.TextFieldState

/**
 * UI State that represents LoginScreen
 **/
data class LoginState(
    val emailState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
    val isLoggingIn: Boolean = false,
)

/**
 * Login Events emitted from the ViewModel
 **/
sealed interface LoginEvent {
    data class Error(
        val message: String,
    ) : LoginEvent

    data object LoginSuccess : LoginEvent
}

/**
 * Login Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class LoginActions(
    val onClickLogin: () -> Unit,
    val onClickSignUp: () -> Unit,
    val onClickSkip: () -> Unit,
    val onClickForgotPassword: () -> Unit,
)
