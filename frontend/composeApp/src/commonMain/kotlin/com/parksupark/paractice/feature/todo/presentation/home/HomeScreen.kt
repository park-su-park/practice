package com.parksupark.paractice.feature.todo.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.parksupark.paractice.feature.todo.presentation.home.components.TodoItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    state: HomeState,
    actions: HomeActions,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = actions.onTodoWriteClick) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add")
            }
        },
    ) { innerPadding ->
        HomeContent(
            state = state,
            actions = actions,
            modifier = Modifier.fillMaxSize().padding(innerPadding),
        )
    }
}

@Composable
private fun HomeContent(
    state: HomeState,
    actions: HomeActions,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(items = state.todos, key = { it.id }) { item ->
            TodoItem(
                todo = item,
                onCheckBoxClick = { actions.onTodoDoneClick(item.id) },
                onExpandedBodyClick = { actions.onTodoBodyClick(item.id) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
@Preview
private fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState(),
        actions = HomeActions(),
    )
}
