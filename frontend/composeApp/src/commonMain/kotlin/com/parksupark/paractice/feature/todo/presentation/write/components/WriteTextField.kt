package com.parksupark.paractice.feature.todo.presentation.write.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.parksupark.paractice.core.designsystem.PTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WriteTextField(
    state: TextFieldState,
    hint: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
) {
    var isFocused by remember { mutableStateOf(false) }
    val decorator = remember(state.text) {
        TextFieldDecorator { innerTextField ->
            Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 16.dp)) {
                if (state.text.isEmpty() && !isFocused) {
                    Text(
                        text = hint,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                            0.4f,
                        ),
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                innerTextField()
            }
        }
    }

    Row(
        modifier = modifier.border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(
                alpha = if (isFocused) 0.8f else 0.1f,
            ),
            shape = MaterialTheme.shapes.small,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            state = state,
            modifier = Modifier.onFocusChanged {
                isFocused = it.isFocused
            },
            textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
            decorator = decorator,
            keyboardOptions = keyboardOptions,
            onKeyboardAction = onKeyboardAction,
        )
    }
}

@Preview
@Composable
private fun WriteTextFieldPreview() {
    PTheme {
        WriteTextField(
            state = TextFieldState(),
            hint = "Title",
        )
    }
}
