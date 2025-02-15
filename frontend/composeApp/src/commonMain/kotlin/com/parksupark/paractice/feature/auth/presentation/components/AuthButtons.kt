package com.parksupark.paractice.feature.auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.parksupark.paractice.core.designsystem.PTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ForgotPasswordButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(
            text = "Forgot password?",
            color = LocalContentColor.current.copy(alpha = 0.6f),
        )
    }
}

@Composable
fun SignUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = LocalTextStyle.current.toSpanStyle().copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Light,
                    ),
                ) {
                    append("Don't have an account? ")
                }
                pushStringAnnotation("SignUp", "SignUp")
                append("Sign up")
                pop()
            },
        )
    }
}

@Composable
fun AuthButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = 16.dp),
        shape = MaterialTheme.shapes.small,
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Composable
@Preview
private fun LoginButtonsPreview() {
    PTheme {
        Column {
            ForgotPasswordButton(
                onClick = { },
            )
            SignUpButton(
                onClick = { },
            )
            AuthButton(
                text = "Login",
                onClick = { },
            )
        }
    }
}
