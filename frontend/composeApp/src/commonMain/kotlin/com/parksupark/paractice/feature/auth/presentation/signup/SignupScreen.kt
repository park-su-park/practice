package com.parksupark.paractice.feature.auth.presentation.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.parksupark.paractice.core.designsystem.components.VerticalSpacer
import com.parksupark.paractice.feature.auth.presentation.components.AuthButton
import com.parksupark.paractice.feature.auth.presentation.components.EmailTextField
import com.parksupark.paractice.feature.auth.presentation.components.PasswordTextField
import com.parksupark.paractice.feature.auth.presentation.components.UsernameTextField
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    state: SignupState,
    actions: SignupActions,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    FilledIconButton(
                        onClick = actions.onClickBack,
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "back")
                    }
                },
            )
        },
    ) { innerPadding ->
        SignupScreenContent(
            state = state,
            actions = actions,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )
    }
}

@Composable
private fun SignupScreenContent(
    state: SignupState,
    actions: SignupActions,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))

        Text(text = "Create your account", style = MaterialTheme.typography.titleMedium)
        VerticalSpacer(height = 20.dp)

        SignupForm(
            usernameState = state.usernameState,
            emailState = state.emailState,
            passwordState = state.passwordState,
            passwordConfirmState = state.confirmPasswordState,
            onClickSignup = actions.onClickSignup,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        VerticalSpacer(24.dp)

        AuthButton(
            text = "Sign up",
            isLoading = state.isSignupLoading,
            onClick = actions.onClickSignup,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun SignupForm(
    usernameState: TextFieldState,
    emailState: TextFieldState,
    passwordState: TextFieldState,
    passwordConfirmState: TextFieldState,
    onClickSignup: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        UsernameTextField(
            state = usernameState,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
            onKeyboardAction = { focusManager.moveFocus(FocusDirection.Next) },
        )
        EmailTextField(
            state = emailState,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
            onKeyboardAction = { focusManager.moveFocus(FocusDirection.Next) },
        )
        PasswordTextField(
            state = passwordState,
            hint = "Password",
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
            ),
            onKeyboardAction = { focusManager.moveFocus(FocusDirection.Next) },
        )
        PasswordTextField(
            state = passwordConfirmState,
            hint = "Confirm Password",
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            onKeyboardAction = { onClickSignup() },
        )
    }
}

@Composable
@Preview
private fun SignupScreenPreview() {
    SignupScreen(
        state = SignupState(),
        actions = SignupActions(),
    )
}
