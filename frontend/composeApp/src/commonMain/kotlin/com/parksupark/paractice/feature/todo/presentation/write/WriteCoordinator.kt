package com.parksupark.paractice.feature.todo.presentation.write

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.compose.viewmodel.koinViewModel

class WriteCoordinator(
    val viewModel: WriteViewModel,
) {
    val screenStateFlow = viewModel.stateFlow
}

@Composable
fun rememberWriteCoordinator(viewModel: WriteViewModel = koinViewModel()): WriteCoordinator {
    return remember(viewModel) {
        WriteCoordinator(
            viewModel = viewModel,
        )
    }
}
