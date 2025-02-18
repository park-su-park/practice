package com.parksupark.paractice.feature.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parksupark.paractice.core.domain.util.Result
import com.parksupark.paractice.feature.auth.domain.usecase.LoginUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _uiStateFlow: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val uiStateFlow: StateFlow<LoginState> = _uiStateFlow.asStateFlow()

    private val _event = Channel<LoginEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    fun performLogin() {
        viewModelScope.launch {
            _uiStateFlow.value = uiStateFlow.value.copy(isLoggingIn = true)
            val result = loginUseCase(
                email = uiStateFlow.value.emailState.text
                    .toString()
                    .trim(),
                password = uiStateFlow.value.passwordState.text
                    .toString(),
            )

            if (result is Result.Success) {
                _event.send(LoginEvent.LoginSuccess)
            } else if (result is Result.Error) {
                _event.send(LoginEvent.Error(result.error.name))
            }
            _uiStateFlow.value = uiStateFlow.value.copy(isLoggingIn = false)
        }
    }

    fun skipLogin() {
        // TODO
    }
}
