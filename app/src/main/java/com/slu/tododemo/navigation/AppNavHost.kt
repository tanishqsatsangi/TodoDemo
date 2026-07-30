package com.slu.tododemo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.slu.tododemo.presentation.CreateTodo
import com.slu.tododemo.presentation.LandingScreen


@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(route = Routes.HOME) {
            LandingScreen(
                modifier,
                {
                    navController.navigate(Routes.CREATE_TODO)
                })
        }
        composable(route = Routes.CREATE_TODO) { CreateTodo() }
    }

}