package com.parksupark.paractice.feature.todo.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.compose.viewmodel.koinViewModel

class HomeCoordinator(
    val viewModel: HomeViewModel,
) {
    val screenStateFlow = viewModel.stateFlow
}

@Composable
fun rememberHomeCoordinator(viewModel: HomeViewModel = koinViewModel()): HomeCoordinator {
    return remember(viewModel) {
        HomeCoordinator(
            viewModel = viewModel,
        )
    }
}
