package com.parksupark.paractice.feature.auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginRoute(coordinator: LoginCoordinator = rememberLoginCoordinator()) {
    // State observing and declarations
    val uiState by coordinator.uiStateFlow.collectAsStateWithLifecycle(LoginState())

    // UI Actions
    val actions = rememberLoginActions(coordinator)

    // UI Rendering
    LoginScreen(state = uiState, actions = actions)
}

@Composable
fun rememberLoginActions(coordinator: LoginCoordinator): LoginActions {
    return remember(coordinator) {
        LoginActions(
            onClickLogin = { coordinator.viewModel.performLogin() },
            onClickSkip = { coordinator.viewModel.skipLogin() },
        )
    }
}
