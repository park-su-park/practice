package com.parksupark.paractice.feature.todo.domain.model

data class Todo(
    val id: Long,
    val title: String,
    val content: String,
    val isDone: Boolean,
)
