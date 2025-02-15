package com.parksupark.paractice.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.parksupark.paractice.feature.auth.presentation.login.LoginRoute
import com.parksupark.paractice.feature.auth.presentation.signup.SignupRoute
import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthRoute {
    @Serializable
    data object Root : AuthRoute

    @Serializable
    data object Login : AuthRoute

    @Serializable
    data object SignUp : AuthRoute
}

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<AuthRoute.Root>(
        startDestination = AuthRoute.Login,
    ) {
        composable<AuthRoute.Login> {
            LoginRoute(
                navigateToHome = { /* TODO */ },
                navigateToSignup = navController::navigateToSignup,
                navigateToForgotPassword = { /* TODO */ },
            )
        }
        composable<AuthRoute.SignUp> {
            SignupRoute(
                navigateUp = navController::navigateUp,
            )
        }
    }
}

private fun NavHostController.navigateToSignup(navOptions: NavOptions? = null) {
    navigate(
        route = AuthRoute.SignUp,
        navOptions = navOptions,
    )
}
