package com.parksupark.paractice.feature.auth.presentation.signup

import androidx.compose.foundation.text.input.TextFieldState

data class SignupState(
    val usernameState: TextFieldState = TextFieldState(),
    val emailState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
    val confirmPasswordState: TextFieldState = TextFieldState(),
    val isSignupLoading: Boolean = false,
)

sealed interface SignupEvent {
    data object SignupSuccess : SignupEvent

    data object SignupFailure : SignupEvent
}

data class SignupActions(
    val onClickBack: () -> Unit = { },
    val onClickSignup: () -> Unit = { },
    val onClick: () -> Unit = { },
)
