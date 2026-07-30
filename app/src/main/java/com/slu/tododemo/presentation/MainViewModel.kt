package com.slu.tododemo.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.slu.tododemo.TodoItem
import com.slu.tododemo.data.TodoEntity
import com.slu.tododemo.data.TodoRepository
import kotlinx.coroutines.launch

class MainViewModel(val appContext: Application) :
    AndroidViewModel(appContext) {

    private val todoRepository by lazy {
        TodoRepository(appContext)
    }

    fun getAllTodos() {
        viewModelScope.launch {
            mapDatatoUiModel(todoRepository.getAllTodos())
        }
    }

    fun getTodoById(id: String) {
        viewModelScope.launch {
            todoRepository.getTodoById(id)
        }
    }

    fun insertTodo(todo: TodoEntity) {
        viewModelScope.launch {
            todoRepository.insertTodo(todo)
        }
    }

    fun mapDatatoUiModel(todoEntity: List<TodoEntity>): List<TodoItem> {
        val listUiModel: MutableList<TodoItem> = mutableListOf()
        for (todo in todoEntity) {
            val todoItem = TodoItem(
                id = todo.id,
                title = todo.title,
                description = todo.description,
                createdOn = todo.createdOn,
                priority = todo.priority
            )
            listUiModel.add(todoItem)
        }
        return listUiModel
    }
}