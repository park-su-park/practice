package com.parksupark.paractice

import androidx.compose.runtime.Composable
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
            val navHostController = rememberNavController()
            NavHost(
                navController = navHostController,
                startDestination = AuthRoute.Root,
            ) {
                authGraph()
            }
        }
    }
}

@Composable
@Preview
private fun AppPreview() {
    App()
}
