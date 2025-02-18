package com.parksupark.paractice.feature.auth.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parksupark.paractice.core.domain.util.Result
import com.parksupark.paractice.feature.auth.domain.usecase.SignupUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignupViewModel(
    private val signupUseCase: SignupUseCase,
) : ViewModel() {
    private val _uiStateFlow: MutableStateFlow<SignupState> = MutableStateFlow(SignupState())
    val uiStateFlow: StateFlow<SignupState> = _uiStateFlow.asStateFlow()

    private val _event = Channel<SignupEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    fun signup() {
        viewModelScope.launch {
            _uiStateFlow.value = _uiStateFlow.value.copy(isSignupLoading = true)

            val (username, email, password) = _uiStateFlow.value

            val response = signupUseCase(
                username.text.toString(),
                email.text.toString(),
                password.text.toString(),
            )

            if (response is Result.Success) {
                _event.send(SignupEvent.SignupSuccess)
            } else if (response is Result.Error) {
                _event.send(SignupEvent.SignupFailure(response.error.name))
            }
            _uiStateFlow.value = _uiStateFlow.value.copy(isSignupLoading = false)
        }
    }
}
