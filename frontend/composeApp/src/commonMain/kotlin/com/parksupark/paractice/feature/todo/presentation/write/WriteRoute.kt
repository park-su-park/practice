package com.parksupark.paractice.feature.todo.presentation.write

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun WriteRoute(
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    navigateUp: () -> Unit,
    coordinator: WriteCoordinator = rememberWriteCoordinator(),
) {
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(WriteState())

    val actions = rememberWriteActions(navigateUp = navigateUp, coordinator = coordinator)

    WriteScreen(state = uiState, actions = actions)
}

@Composable
fun rememberWriteActions(
    navigateUp: () -> Unit,
    coordinator: WriteCoordinator,
): WriteActions {
    return remember(coordinator) {
        WriteActions(
            onBackClick = navigateUp,
        )
    }
}
