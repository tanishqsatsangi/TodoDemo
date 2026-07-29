package com.slu.tododemo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.slu.tododemo.Priority

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey  val id: String,
    val title: String,
    val description: String,
    val createdOn: Long,
    val priority: Priority
)