package com.parksupark.paractice.feature.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.parksupark.paractice.core.designsystem.PTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun UsernameTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
) {
    AuthTextField(
        state = state,
        startIcon = Icons.Outlined.Person,
        hint = "Username",
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
    )
}

@Composable
fun EmailTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
) {
    AuthTextField(
        state = state,
        startIcon = Icons.Outlined.Email,
        hint = "Email",
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
    )
}

@Composable
fun PasswordTextField(
    state: TextFieldState,
    hint: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
) {
    AuthTextField(
        state = state,
        startIcon = Icons.Outlined.Key,
        hint = hint,
        modifier = modifier,
        isPassword = true,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
    )
}

@Composable
private fun AuthTextField(
    state: TextFieldState,
    startIcon: ImageVector,
    hint: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
) {
    var isFocused by remember { mutableStateOf(false) }
    val decorator = remember(state.text) {
        TextFieldDecorator { innerTextField ->
            Box(modifier = Modifier.fillMaxWidth().padding(start = 20.dp)) {
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
                alpha = 0.1f,
            ),
            shape = MaterialTheme.shapes.small,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = startIcon,
            contentDescription = null,
            modifier = Modifier
                .graphicsLayer {
                    shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                    clip = true
                }.background(
                    color = MaterialTheme.colorScheme.primary,
                ).padding(16.dp),
            tint = MaterialTheme.colorScheme.onPrimary,
        )
        if (isPassword) {
            BasicSecureTextField(
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
        } else {
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
}

@Composable
@Preview
private fun AuthTextFieldPreview() {
    PTheme {
        Column {
            EmailTextField(
                state = TextFieldState(),
                keyboardOptions = KeyboardOptions.Default,
                onKeyboardAction = { },
            )
            PasswordTextField(
                state = TextFieldState(),
                hint = "Password",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                onKeyboardAction = { },
            )
        }
    }
}
