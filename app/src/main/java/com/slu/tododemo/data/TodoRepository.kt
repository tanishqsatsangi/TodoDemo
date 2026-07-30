package com.slu.tododemo.data

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.flow.Flow

// Room-based implementation of the repository contract.
class TodoRepository(private val appContext: Application) : TodoRepositoryContract {

    private val database by lazy {
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "todo.db"
        ).build()
    }

    // Active approach: observe the table and emit updates automatically.
    override fun observeTodos(): Flow<List<TodoEntity>> {
        return database.getDao().observeTodos()
    }

    // Single-shot alternative (kept as reference).
    // suspend fun getAllTodos(): List<TodoEntity> {
    //     return database.getDao().getAllTodos()
    // }

    override suspend fun getTodoById(id: String): TodoEntity? {
        return database.getDao().getTodoById(id)
    }

    override suspend fun insertTodo(todo: TodoEntity) {
        database.getDao().insertTodo(todo)
    }
}
