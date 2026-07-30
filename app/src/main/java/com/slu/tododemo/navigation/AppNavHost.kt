package com.slu.tododemo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.slu.tododemo.presentation.CreateTodo
import com.slu.tododemo.presentation.LandingScreen
import com.slu.tododemo.presentation.MainViewModel

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
) {
    // Shared VM instance so both screens write/read the same state holder.
    val mainViewModel: MainViewModel = viewModel()

    // Architecture note:
    // - Use separate VMs when screens are independent (list state vs form state).
    // - Use a shared VM when screens must share in-memory state/events directly.
    // - In this app, separate VMs can still work well if Landing observes Room Flow
    //   and Create writes to DB, because DB is the shared source of truth.
    // - Shared VM is simpler here while learning and avoids coordination mistakes.



    // Reference only (not recommended):
    // If each destination creates its own VM instance, you lose shared in-memory
    // state coordination between screens and rely only on DB round-trips.
    // composable(route = Routes.HOME) {
    //     LandingScreen(
    //         modifier = modifier,
    //         onFabClick = { navController.navigate(Routes.CREATE_TODO) },
    //         mainViewModel = viewModel()
    //     )
    // }
    // composable(route = Routes.CREATE_TODO) {
    //     CreateTodo(
    //         mainViewModel = viewModel(),
    //         onSaveSuccess = { navController.popBackStack() }
    //     )
    // }

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(route = Routes.HOME) {
            LandingScreen(
                modifier = modifier,
                onFabClick = { navController.navigate(Routes.CREATE_TODO) },
                mainViewModel = mainViewModel
            )
        }
        composable(route = Routes.CREATE_TODO) {
            CreateTodo(
                mainViewModel = mainViewModel,
                onSaveSuccess = { navController.popBackStack() }
            )
        }
    }
}
