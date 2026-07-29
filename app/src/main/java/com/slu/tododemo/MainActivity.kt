package com.slu.tododemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.slu.tododemo.ui.theme.TodoDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LandingScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun LandingScreen(modifier: Modifier){
    val listOfTodo = mutableListOf<TodoItem>()
    Column {
        LazyColumn {
                items (listOfTodo){
                    TodoItemComposable(todoItem = it)
                }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoDemoTheme {
        LandingScreen(modifier = Modifier)
    }
}