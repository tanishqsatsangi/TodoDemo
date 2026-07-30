package com.slu.tododemo.data

import kotlinx.coroutines.flow.Flow

// Repository contract to allow multiple data sources later (Room, backend, mock).
// Important: this interface does not create instances by itself.
// It only defines the API shape. A caller (ViewModel/factory/DI) must decide
// which concrete implementation to create and provide at runtime.
interface TodoRepositoryContract {
    // Active approach: stream updates as DB/network data changes.
    fun observeTodos(): Flow<List<TodoEntity>>

    // Single-shot alternative (kept as reference).
    // suspend fun getAllTodos(): List<TodoEntity>

    suspend fun getTodoById(id: String): TodoEntity?
    suspend fun insertTodo(todo: TodoEntity)
}
