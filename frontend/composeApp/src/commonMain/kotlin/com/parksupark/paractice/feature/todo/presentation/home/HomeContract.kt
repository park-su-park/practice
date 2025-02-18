package com.parksupark.paractice.feature.todo.presentation.home

import com.parksupark.paractice.feature.todo.presentation.home.model.TodoUi

data class HomeState(
    val todos: List<TodoUi> = emptyList(),
    val isTodosLoading: Boolean = false,
)

data class HomeActions(
    val onTodoDoneClick: (todoId: Long) -> Unit = { },
    val onTodoBodyClick: (todoId: Long) -> Unit = { },
)
