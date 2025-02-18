package com.parksupark.paractice

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.parksupark.paractice.core.designsystem.PTheme
import com.parksupark.paractice.feature.auth.navigation.AuthRoute
import com.parksupark.paractice.feature.auth.navigation.authGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
fun App() {
    PTheme {
        KoinContext {
            val snackbarHostState = remember { SnackbarHostState() }

            val navHostController = rememberNavController()

            Scaffold(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                snackbarHost = {
                    SnackbarHost(modifier = Modifier.imePadding(), hostState = snackbarHostState, snackbar = {
                        Snackbar(snackbarData = it)
                    })
                },
            ) {
                val onShowSnackbar: suspend (String, String?) -> Boolean = remember {
                    { message, actionLabel ->
                        snackbarHostState.showSnackbar(
                            message = message,
                            actionLabel = actionLabel,
                            withDismissAction = true,
                            duration = SnackbarDuration.Short,
                        ) == SnackbarResult.ActionPerformed
                    }
                }

                NavHost(
                    navController = navHostController,
                    startDestination = AuthRoute.Root,
                ) {
                    authGraph(navController = navHostController, onShowSnackbar = onShowSnackbar)
                }
            }
        }
    }
}

@Composable
@Preview
private fun AppPreview() {
    App()
}
