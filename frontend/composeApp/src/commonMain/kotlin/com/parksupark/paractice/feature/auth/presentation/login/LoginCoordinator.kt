package com.parksupark.paractice.feature.auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.compose.viewmodel.koinViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class LoginCoordinator(
    val viewModel: LoginViewModel,
) {
    val uiStateFlow = viewModel.uiStateFlow
    val event = viewModel.event
}

@Composable
fun rememberLoginCoordinator(viewModel: LoginViewModel = koinViewModel()): LoginCoordinator {
    return remember(viewModel) {
        LoginCoordinator(
            viewModel = viewModel,
        )
    }
}
