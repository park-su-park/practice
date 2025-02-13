package com.parksupark.paractice.feature.auth.presentation.login

/**
 * UI State that represents LoginScreen
 **/
class LoginState

/**
 * Login Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class LoginActions(
    val onClickLogin: () -> Unit,
    val onClickSkip: () -> Unit,
)
