package com.parksupark.paractice.feature.auth.presentation.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiStateFlow: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val uiStateFlow: StateFlow<LoginState> = _uiStateFlow.asStateFlow()

    fun performLogin() {
        // TODO
    }

    fun skipLogin() {
        // TODO
    }
}
