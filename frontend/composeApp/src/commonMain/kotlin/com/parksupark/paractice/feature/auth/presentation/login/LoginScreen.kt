package com.parksupark.paractice.feature.auth.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.parksupark.paractice.core.designsystem.PTheme
import com.parksupark.paractice.core.designsystem.components.VerticalSpacer
import com.parksupark.paractice.feature.auth.presentation.components.EmailTextField
import com.parksupark.paractice.feature.auth.presentation.components.ForgotPasswordButton
import com.parksupark.paractice.feature.auth.presentation.components.AuthButton
import com.parksupark.paractice.feature.auth.presentation.components.PasswordTextField
import com.parksupark.paractice.feature.auth.presentation.components.SignUpButton
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
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Login to your account", style = MaterialTheme.typography.titleMedium)
        VerticalSpacer(height = 20.dp)

        val (first, second) = remember { FocusRequester.createRefs() }
        EmailTextField(
            state = emailState,
            modifier = Modifier
                .focusRequester(first)
                .padding(horizontal = 16.dp),
            onKeyboardAction = {
                second.requestFocus()
            },
        )
        VerticalSpacer(height = 20.dp)

        PasswordTextField(
            state = passwordState,
            hint = "Password",
            modifier = Modifier
                .focusRequester(second)
                .padding(horizontal = 16.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            onKeyboardAction = {
                actions.onClickLogin()
            },
        )
        ForgotPasswordButton(
            onClick = actions.onClickForgotPassword,
            modifier = Modifier.align(Alignment.End),
        )
        VerticalSpacer(height = 24.dp)

        AuthButton(
            text = "Login",
            isLoading = state.isLoggingIn,
            onClick = actions.onClickLogin,
            modifier = Modifier.fillMaxWidth(),
        )
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
