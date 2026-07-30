package com.slu.tododemo.presentation

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.slu.tododemo.TodoItem
import com.slu.tododemo.ui.theme.TodoDemoTheme

@Composable
fun LandingScreen(modifier: Modifier, onFabClick: () -> Unit) {
    val mainViewModel: MainViewModel = viewModel()
    val list = mainViewModel.getAllTodos()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onFabClick()
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Todo")
            }
        }
    ) {
        val modifier: Modifier = Modifier.padding(it)
        if (list.isEmpty()) {
            Text(
                text = "No Todo Items Found",
                modifier = modifier
            )
        } else {
            Column {
                LazyColumn {
                    items(listOf<TodoItem>(), key = { it.id }) {
                        TodoItemComposable(todoItem = it)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoDemoTheme {
        LandingScreen(modifier = Modifier, onFabClick = {})
    }
}