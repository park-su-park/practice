package com.parksupark.paractice.feature.auth.presentation.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.compose.viewmodel.koinViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class SignupCoordinator(
    val viewModel: SignupViewModel,
) {
    val screenStateFlow = viewModel.uiStateFlow
}

@Composable
fun rememberSignupCoordinator(viewModel: SignupViewModel = koinViewModel()): SignupCoordinator {
    return remember(viewModel) {
        SignupCoordinator(
            viewModel = viewModel,
        )
    }
}
