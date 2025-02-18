package com.parksupark.paractice.feature.todo.presentation.home.model

import com.parksupark.paractice.feature.todo.domain.model.Todo

data class TodoUi(
    val id: Long,
    val title: String,
    val content: String,
    val isDone: Boolean,
)

fun Todo.toUi() =
    TodoUi(
        id = id,
        title = title,
        content = content,
        isDone = isDone,
    )
