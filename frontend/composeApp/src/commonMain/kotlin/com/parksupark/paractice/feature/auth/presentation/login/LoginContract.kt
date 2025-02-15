package com.parksupark.paractice.feature.auth.presentation.login

import androidx.compose.foundation.text.input.TextFieldState

/**
 * UI State that represents LoginScreen
 **/
data class LoginState(
    val emailState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
)

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
