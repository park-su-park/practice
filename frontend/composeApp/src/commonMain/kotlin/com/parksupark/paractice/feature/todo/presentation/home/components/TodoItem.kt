package com.parksupark.paractice.feature.todo.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.parksupark.paractice.core.designsystem.PTheme
import com.parksupark.paractice.core.designsystem.components.VerticalSpacer
import com.parksupark.paractice.feature.todo.presentation.home.model.TodoUi
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TodoItem(
    todo: TodoUi,
    onCheckBoxClick: () -> Unit,
    onExpandedBodyClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isExpanded by remember { mutableStateOf(false) }

    OutlinedCard(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = todo.title, style = MaterialTheme.typography.titleMedium)
                Checkbox(
                    checked = todo.isDone,
                    onCheckedChange = { onCheckBoxClick() },
                    modifier = Modifier.padding(start = 16.dp),
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                VerticalSpacer(height = 24.dp)
                Text(
                    text = todo.content,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onExpandedBodyClick),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview
@Composable
private fun TodoItemPreview() {
    PTheme {
        TodoItem(
            todo = TodoUi(
                id = 1,
                title = "Sample Title",
                content = "Sample content for the todo item.",
                isDone = false,
            ),
            onCheckBoxClick = { },
            onExpandedBodyClick = { },
        )
    }
}
