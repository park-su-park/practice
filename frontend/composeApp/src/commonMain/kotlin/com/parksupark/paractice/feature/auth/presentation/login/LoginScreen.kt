package com.parksupark.paractice.feature.auth.presentation.login

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(
    state: LoginState,
    actions: LoginActions,
) {
    // TODO UI Rendering
}

@Composable
@Preview
private fun LoginScreenPreview() {
    LoginScreen(
        state = LoginState(),
        actions = LoginActions(
            onClickLogin = { },
            onClickSkip = { },
        ),
    )
}
