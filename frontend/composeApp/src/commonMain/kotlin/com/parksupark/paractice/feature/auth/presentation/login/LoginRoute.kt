package com.parksupark.paractice.feature.auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.parksupark.paractice.core.presentation.ObserveAsEvents
import kotlinx.coroutines.launch

@Composable
fun LoginRoute(
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    navigateToHome: () -> Unit,
    navigateToSignup: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    coordinator: LoginCoordinator = rememberLoginCoordinator(),
) {
    // State observing and declarations
    val uiState by coordinator.uiStateFlow.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    // Events observing
    ObserveAsEvents(coordinator.event) { event ->
        when (event) {
            is LoginEvent.LoginSuccess -> {
                navigateToHome()
            }

            is LoginEvent.Error -> {
                scope.launch {
                    onShowSnackbar(event.message, null)
                }
            }
        }
    }

    // UI Actions
    val actions = rememberLoginActions(
        navigateToSignup = navigateToSignup,
        navigateToForgotPassword = navigateToForgotPassword,
        coordinator = coordinator,
    )

    // UI Rendering
    LoginScreen(state = uiState, actions = actions)
}

@Composable
fun rememberLoginActions(
    navigateToSignup: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    coordinator: LoginCoordinator,
): LoginActions {
    return remember(navigateToSignup, navigateToForgotPassword, coordinator) {
        LoginActions(
            onClickLogin = { coordinator.viewModel.performLogin() },
            onClickSignUp = navigateToSignup,
            onClickSkip = { coordinator.viewModel.skipLogin() },
            onClickForgotPassword = navigateToForgotPassword,
        )
    }
}
