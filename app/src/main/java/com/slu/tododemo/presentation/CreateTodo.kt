package com.slu.tododemo.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@Composable
fun CreateTodo() {

    var titleState: String by remember { mutableStateOf("") }
    var descState: String by remember { mutableStateOf("") }
    Column {
        TextField(
            value = titleState,
            onValueChange = { /* Handle title change */
                titleState = it
            },
            label = { androidx.compose.material3.Text("Title") }

        )
        TextField(
            value = descState,
            onValueChange = { /* Handle description change */
                descState = it
            },
            label = { androidx.compose.material3.Text("Description") }
        )
        Button(
            onClick = {
                //handle action
            },
            modifier = Modifier.padding(8.dp),

        ) {
            Text("Save")
        }
    }
}