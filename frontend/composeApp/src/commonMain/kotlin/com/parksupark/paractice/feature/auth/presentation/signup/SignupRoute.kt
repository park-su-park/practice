package com.parksupark.paractice.feature.auth.presentation.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SignupRoute(
    navigateUp: () -> Unit,
    coordinator: SignupCoordinator = rememberSignupCoordinator(),
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()

    // UI Actions
    val actions = rememberSignupActions(
        navigateUp = navigateUp,
        coordinator = coordinator,
    )

    // UI Rendering
    SignupScreen(state = uiState, actions = actions)
}

@Composable
fun rememberSignupActions(
    navigateUp: () -> Unit,
    coordinator: SignupCoordinator,
): SignupActions {
    return remember(coordinator) {
        SignupActions(
            onClickBack = navigateUp,
            onClickSignup = { },
        )
    }
}
