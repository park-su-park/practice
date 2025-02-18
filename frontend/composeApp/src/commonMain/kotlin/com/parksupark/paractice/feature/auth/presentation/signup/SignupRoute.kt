package com.parksupark.paractice.feature.auth.presentation.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.parksupark.paractice.core.presentation.ObserveAsEvents
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun SignupRoute(
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    navigateUp: () -> Unit,
    coordinator: SignupCoordinator = rememberSignupCoordinator(),
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    ObserveAsEvents(coordinator.viewModel.event) { event ->
        when (event) {
            SignupEvent.SignupSuccess -> {
                navigateUp()
            }

            is SignupEvent.SignupFailure -> {
                coroutineScope.launch {
                    onShowSnackbar("Signup failed: ${event.message}", null)
                }
            }
        }
    }

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
            onClickSignup = coordinator.viewModel::signup,
        )
    }
}
