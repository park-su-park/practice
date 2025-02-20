package com.parksupark.paractice.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.parksupark.paractice.feature.auth.presentation.login.LoginRoute
import com.parksupark.paractice.feature.auth.presentation.signup.SignupRoute
import com.parksupark.paractice.feature.todo.navigation.navigateToTodoHome
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

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
) {
    navigation<AuthRoute.Root>(
        startDestination = AuthRoute.Login,
    ) {
        composable<AuthRoute.Login> {
            LoginRoute(
                onShowSnackbar = onShowSnackbar,
                navigateToHome = {
                    navController.navigateToTodoHome(
                        navOptions {
                            popUpTo(AuthRoute.Root) { inclusive = true }
                            launchSingleTop = true
                        },
                    )
                },
                navigateToSignup = navController::navigateToSignup,
                navigateToForgotPassword = { /* TODO */ },
            )
        }
        composable<AuthRoute.SignUp> {
            SignupRoute(
                onShowSnackbar = onShowSnackbar,
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
