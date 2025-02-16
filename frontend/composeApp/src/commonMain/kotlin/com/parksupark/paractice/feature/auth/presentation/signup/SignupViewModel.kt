package com.parksupark.paractice.feature.auth.presentation.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignupViewModel : ViewModel() {
    private val _uiStateFlow: MutableStateFlow<SignupState> = MutableStateFlow(SignupState())
    val uiStateFlow: StateFlow<SignupState> = _uiStateFlow.asStateFlow()
}
