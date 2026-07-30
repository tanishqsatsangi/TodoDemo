package com.slu.tododemo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.ABORT
import androidx.room.Query


@Dao
interface TodoDao {

    @Insert(onConflict = ABORT)
    fun insertTodo(todo: TodoEntity)

    @Delete
    fun deleteTodo(todo: TodoEntity)

    @Query("SELECT * FROM todos")
    fun getAllTodos(): List<TodoEntity>


    @Query("SELECT * FROM todos WHERE id = :id")
    fun getTodoById(id: String): TodoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertTodo(todo: TodoEntity)

}