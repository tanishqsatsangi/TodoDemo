package com.slu.tododemo.data

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class TodoRepository(private val appContext: Application) {

    private val database by lazy {
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "todo.db"
        ).build()
    }


    suspend fun getAllTodos(): Flow<TodoEntity> {
        return database.getDao().getAllTodos().asFlow()

    }

    suspend fun getTodoById(id: String): TodoEntity? {
        return database.getDao().getTodoById(id)
    }


    suspend fun insertTodo(todo: TodoEntity) {
        return database.getDao().insertTodo(todo)
    }

}