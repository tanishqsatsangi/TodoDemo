package com.slu.tododemo.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.slu.tododemo.TodoItem
import com.slu.tododemo.data.TodoEntity
import com.slu.tododemo.data.TodoRepository
import com.slu.tododemo.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(val appContext: Application) :
    AndroidViewModel(appContext) {

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos.asStateFlow()

    private val _todoById = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoById: StateFlow<List<TodoItem>> = _todos.asStateFlow()


    private val todoRepository by lazy {
        TodoRepository(appContext)
    }

    fun getAllTodos() {
        viewModelScope.launch {
            todoRepository.getAllTodos().collect { list ->
                _todos.value = list.toUi()
            }

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