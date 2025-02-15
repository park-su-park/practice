package com.parksupark.paractice.feature.auth.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.parksupark.paractice.core.designsystem.PTheme
import com.parksupark.paractice.core.designsystem.components.VerticalSpacer
import com.parksupark.paractice.feature.auth.presentation.login.components.EmailTextField
import com.parksupark.paractice.feature.auth.presentation.login.components.ForgotPasswordButton
import com.parksupark.paractice.feature.auth.presentation.login.components.LoginButton
import com.parksupark.paractice.feature.auth.presentation.login.components.PasswordTextField
import com.parksupark.paractice.feature.auth.presentation.login.components.SignUpButton
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(
    state: LoginState,
    actions: LoginActions,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        LoginContent(
            state = state,
            actions = actions,
            modifier = Modifier.fillMaxSize().padding(innerPadding),
        )
    }
}

@Composable
private fun LoginContent(
    state: LoginState,
    actions: LoginActions,
    modifier: Modifier = Modifier,
) {
    val emailState = state.emailState
    val passwordState = state.passwordState

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Login to your account", style = MaterialTheme.typography.titleMedium)
        VerticalSpacer(height = 20.dp)

        val (first, second) = remember { FocusRequester.createRefs() }
        EmailTextField(
            state = emailState,
            onKeyboardAction = {
                second.requestFocus()
            },
            modifier = Modifier
                .focusRequester(first)
                .padding(horizontal = 16.dp),
        )
        VerticalSpacer(height = 20.dp)

        PasswordTextField(
            state = passwordState,
            onKeyboardAction = {
                actions.onClickLogin()
            },
            modifier = Modifier
                .focusRequester(second)
                .padding(horizontal = 16.dp),
        )
        ForgotPasswordButton(
            onClick = actions.onClickForgotPassword,
            modifier = Modifier.align(Alignment.End),
        )
        VerticalSpacer(height = 24.dp)

        LoginButton(onClick = actions.onClickLogin)
        VerticalSpacer(height = 24.dp)

        SignUpButton(
            onClick = actions.onClickSignUp,
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}

@Composable
@Preview
private fun LoginScreenPreview() {
    PTheme {
        LoginScreen(
            state = LoginState(),
            actions = LoginActions(
                onClickLogin = { },
                onClickSignUp = { },
                onClickSkip = { },
                onClickForgotPassword = { },
            ),
        )
    }
}
