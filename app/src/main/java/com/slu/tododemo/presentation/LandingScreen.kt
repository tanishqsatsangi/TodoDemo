package com.slu.tododemo.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit,
    mainViewModel: MainViewModel = viewModel(),
) {
    val todoList by mainViewModel.todos.collectAsStateWithLifecycle()

    // If this screen creates its own VM instance using viewModel() while CreateTodo
    // also creates another VM instance, each screen can end up with separate state
    // holders. The DB still stores data, but in-memory screen state is not shared.
    // Shared VM from AppNavHost keeps both screens connected to one state owner.
    // val localVm: MainViewModel = viewModel()

    // What LaunchedEffect does:
    // - Runs a coroutine tied to this composable's lifecycle.
    // - Re-runs when its key changes; cancels when composable leaves composition.
    // Why we usually use it in single-shot pattern:
    // - Trigger one-time calls like vm.getAllTodos() when screen enters.
    // Why we do not need it in current Flow pattern:
    // - MainViewModel starts collecting observeTodos() in init, so UI updates
    //   automatically without manually triggering load from this composable.
    // Example single-shot trigger:
    // LaunchedEffect(Unit) { mainViewModel.getAllTodos() }

    // Single-shot alternative (kept as reference).
    // androidx.lifecycle.compose.LifecycleResumeEffect(Unit) {
    //     mainViewModel.getAllTodos()
    //     onPauseOrDispose { }
    // }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(Icons.Filled.Add, contentDescription = "Add Todo")
            }
        }
    ) { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)
        if (todoList.isEmpty()) {
            Text(
                text = "No Todo Items Found",
                modifier = contentModifier
            )
        } else {
            // When a new todo is inserted in CreateTodo:
            // 1) Room updates the todos table.
            // 2) observeTodos() emits a fresh list.
            // 3) MainViewModel updates _todos.
            // 4) collectAsStateWithLifecycle receives new state and recomposes.
            // 5) LazyColumn re-renders and shows the new item.
            LazyColumn(modifier = contentModifier) {
                items(todoList, key = { it.id }) { todo ->
                    TodoItemComposable(todoItem = todo)
                }
            }
        }
    }
}
