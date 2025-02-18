package com.parksupark.paractice.feature.todo.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeRoute(
    navigateToTodoDetails: () -> Unit,
    coordinator: HomeCoordinator = rememberHomeCoordinator(),
) {
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(HomeState())

    val actions = rememberHomeActions(
        navigateToTodoDetails = navigateToTodoDetails,
        coordinator = coordinator,
    )

    HomeScreen(uiState, actions)
}

@Composable
fun rememberHomeActions(
    navigateToTodoDetails: () -> Unit,
    coordinator: HomeCoordinator,
): HomeActions {
    val viewmodel = coordinator.viewModel
    return remember(coordinator) {
        HomeActions(
            onTodoDoneClick = { viewmodel.toggleTodoState(it) },
            onTodoBodyClick = { navigateToTodoDetails() },
        )
    }
}
