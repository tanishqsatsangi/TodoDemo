package com.slu.tododemo

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
