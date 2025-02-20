package com.parksupark.paractice.feature.todo.presentation.write

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun WriteRoute(coordinator: WriteCoordinator = rememberWriteCoordinator()) {
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(WriteState())

    val actions = rememberWriteActions(coordinator = coordinator)

    WriteScreen(state = uiState, actions = actions)
}

@Composable
fun rememberWriteActions(coordinator: WriteCoordinator): WriteActions {
    return remember(coordinator) {
        WriteActions()
    }
}
