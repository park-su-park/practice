package com.parksupark.paractice.feature.auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.parksupark.paractice.core.presentation.ObserveAsEvents

@Composable
fun LoginRoute(
    navigateToHome: () -> Unit,
    navigateToSignup: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    coordinator: LoginCoordinator = rememberLoginCoordinator(),
) {
    // State observing and declarations
    val uiState by coordinator.uiStateFlow.collectAsStateWithLifecycle()

    // Events observing
    ObserveAsEvents(coordinator.event) { event ->
        when (event) {
            is LoginEvent.LoginSuccess -> {
                navigateToHome()
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
