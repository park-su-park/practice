package com.parksupark.paractice.feature.todo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.parksupark.paractice.feature.todo.presentation.home.HomeRoute
import com.parksupark.paractice.feature.todo.presentation.write.WriteRoute
import kotlinx.serialization.Serializable

@Serializable
sealed interface TodoRoute {
    @Serializable
    data object Root : TodoRoute

    @Serializable
    data object Home : TodoRoute

    @Serializable
    data object Write : TodoRoute

    @Serializable
    data class Detail(
        val todoId: String,
    ) : TodoRoute
}

fun NavGraphBuilder.todoGraph(
    navController: NavHostController,
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
) {
    navigation<TodoRoute.Root>(
        startDestination = TodoRoute.Home,
    ) {
        composable<TodoRoute.Home> {
            HomeRoute(
                navigateToTodoDetails = { /*TODO: implement navigate to todo detail*/ },
                navigateToTodoWrite = { navController.navigateToTodoWrite() },
            )
        }
        composable<TodoRoute.Write> {
            WriteRoute(
                onShowSnackbar = onShowSnackbar,
                navigateUp = navController::navigateUp,
            )
        }
    }
}

fun NavHostController.navigateToTodoHome(navOptions: NavOptions? = null) {
    navigate(route = TodoRoute.Home, navOptions = navOptions)
}

private fun NavHostController.navigateToTodoWrite(navOptions: NavOptions? = null) {
    navigate(route = TodoRoute.Write, navOptions = navOptions)
}
