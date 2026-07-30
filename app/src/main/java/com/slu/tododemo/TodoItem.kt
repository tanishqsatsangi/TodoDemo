package com.slu.tododemo

import com.slu.tododemo.data.TodoEntity

data class TodoItem(
    val id: String,
    val title: String,
    val description: String,
    val createdOn: Long,
    val priority: Priority
)


enum class Priority {
    HIGH,
    MEDIUM,
    LOW
}

 fun TodoEntity.toUi() = TodoItem(
    id = id,
    title = title,
    description = description,
    createdOn = createdOn,
    priority = priority
)

 fun TodoItem.toEntity() = TodoEntity(
    id = id,
    title = title,
    description = description,
    createdOn = createdOn,
    priority = priority
)


