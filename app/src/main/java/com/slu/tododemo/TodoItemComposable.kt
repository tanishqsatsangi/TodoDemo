package com.slu.tododemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TodoItemComposable(todoItem: TodoItem) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Title")
        Text("Subtitle")

        Row() {
            Text("Priority")
            // Spacer(Modifier.weight(1f))
            Button(
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.size(48.dp),
                onClick = {
                    // some action
                }) {
                Image(
                    painter = painterResource(id = R.drawable.outline_box_edit_24),
                    contentDescription = "Edit",
                    modifier = Modifier
                )

            }
            Button(
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.size(48.dp),
                onClick =
                    {
                        //some action
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_done),
                    contentDescription = "Edit",
                    modifier = Modifier
                )
            }
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TodoItemPreview() {
    TodoItemComposable(TodoItem("Title", "Subtitle", "", 123L, Priority.HIGH))

}