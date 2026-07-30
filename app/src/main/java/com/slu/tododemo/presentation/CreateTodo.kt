package com.slu.tododemo.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.slu.tododemo.Priority
import com.slu.tododemo.data.TodoEntity
import java.util.UUID

@Composable
fun CreateTodo(
    mainViewModel: MainViewModel,
    onSaveSuccess: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var titleState by remember { mutableStateOf("") }
    var descState by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        TextField(
            value = titleState,
            onValueChange = { titleState = it },
            label = { Text("Title") }
        )
        TextField(
            value = descState,
            onValueChange = { descState = it },
            label = { Text("Description") }
        )
        Button(
            onClick = {
                val title = titleState.trim()
                if (title.isEmpty()) return@Button

                val todo = TodoEntity(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    description = descState.trim(),
                    createdOn = System.currentTimeMillis(),
                    priority = Priority.MEDIUM
                )

                // Persist and then return to landing.
                mainViewModel.insertTodo(todo) {
                    onSaveSuccess()
                }
            },
            modifier = Modifier.padding(8.dp),
        ) {
            Text("Save")
        }
    }
}
