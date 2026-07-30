package com.slu.tododemo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.ABORT
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = ABORT)
    suspend fun insertTodo(todo: TodoEntity)

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

    // Active approach: Flow emits whenever the table changes.
    @Query("SELECT * FROM todos ORDER BY createdOn DESC")
    fun observeTodos(): Flow<List<TodoEntity>>

    // Single-shot alternative (kept as reference).
    // @Query("SELECT * FROM todos ORDER BY createdOn DESC")
    // suspend fun getAllTodos(): List<TodoEntity>

    @Query("SELECT * FROM todos WHERE id = :id")
    suspend fun getTodoById(id: String): TodoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTodo(todo: TodoEntity)
}
