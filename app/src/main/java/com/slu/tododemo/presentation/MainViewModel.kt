package com.slu.tododemo.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.slu.tododemo.TodoItem
import com.slu.tododemo.data.TodoEntity
import com.slu.tododemo.data.TodoRepositoryContract
import com.slu.tododemo.data.TodoRepository
import com.slu.tododemo.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(appContext: Application) : AndroidViewModel(appContext) {

    // Depend on contract so implementation can be swapped (Room/backend/mock).
    // Current selection happens here:
    // - Type is TodoRepositoryContract (abstraction).
    // - Instance is TodoRepository(appContext) (Room implementation).
    // Later this line can be replaced with BackendTodoRepository(...) or provided
    // by DI (Hilt/Koin) without changing ViewModel business logic.
    private val todoRepository: TodoRepositoryContract by lazy { TodoRepository(appContext) }

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos.asStateFlow()

    private val _todoById = MutableStateFlow<TodoItem?>(null)
    val todoById: StateFlow<TodoItem?> = _todoById.asStateFlow()

    init {
        // Active approach: keep collecting DB updates via Flow.
        observeTodos()
    }

    private fun observeTodos() {
        viewModelScope.launch {
            todoRepository.observeTodos().collect { entities ->
                _todos.value = entities.map { it.toUi() }
            }
        }
    }

    // Single-shot alternative (kept as reference).
    // fun getAllTodos() {
    //     viewModelScope.launch {
    //         _todos.value = todoRepository.getAllTodos().map { it.toUi() }
    //     }
    // }

    fun getTodoById(id: String) {
        viewModelScope.launch {
            _todoById.value = todoRepository.getTodoById(id)?.toUi()
        }
    }

    fun insertTodo(todo: TodoEntity, onComplete: (() -> Unit)? = null) {
        viewModelScope.launch {
            todoRepository.insertTodo(todo)
            // Single-shot alternative: call getAllTodos() after insert.
            onComplete?.invoke()
        }
    }
}
