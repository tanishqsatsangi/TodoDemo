package com.slu.tododemo.data

import android.app.Application
import androidx.room.Room

class TodoRepository(private val appContext: Application) {

    private val database by lazy {
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "todo.db"
        ).build()
    }


    suspend fun getAllTodos(): List<TodoEntity> {
        return database.getDao().getAllTodos()

    }

    suspend fun getTodoById(id: String): TodoEntity? {
        return database.getDao().getTodoById(id)
    }


    suspend fun insertTodo(todo: TodoEntity) {
        return database.getDao().insertTodo(todo)
    }

}