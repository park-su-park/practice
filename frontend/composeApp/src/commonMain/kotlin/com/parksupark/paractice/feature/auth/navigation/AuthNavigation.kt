package com.parksupark.paractice.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.parksupark.paractice.feature.auth.presentation.login.LoginRoute
import kotlinx.serialization.Serializable

@Serializable
sealed class AuthRoute {
    @Serializable
    data object Root : AuthRoute()

    @Serializable
    data object Login : AuthRoute()
}

fun NavGraphBuilder.authGraph() {
    navigation<AuthRoute.Root>(
        startDestination = AuthRoute.Login,
    ) {
        composable<AuthRoute.Login> {
            LoginRoute()
        }
    }
}
