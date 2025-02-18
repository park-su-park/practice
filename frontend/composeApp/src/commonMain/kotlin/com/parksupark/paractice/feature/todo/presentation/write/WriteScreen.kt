package com.parksupark.paractice.feature.todo.presentation.write

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WriteScreen(
    state: WriteState,
    actions: WriteActions,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        WriteContent(modifier = Modifier.fillMaxSize().padding(innerPadding))
    }
}

@Composable
private fun WriteContent(modifier: Modifier = Modifier) {
    TODO("Not yet implemented")
}

@Composable
@Preview
private fun WriteScreenPreview() {
    WriteScreen(
        state = WriteState(),
        actions = WriteActions(),
    )
}
