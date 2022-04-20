package com.tanvir.training.todoappbatch04.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tanvir.training.todoappbatch04.db.TodoDatabase
import com.tanvir.training.todoappbatch04.entities.TodoModel
import com.tanvir.training.todoappbatch04.repos.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoDao = TodoDatabase.getDB(application).todoDao()
    private val repository = TodoRepository(todoDao)
    fun insertTodo(todoModel: TodoModel) {
        viewModelScope.launch {
            repository.insertTodo(todoModel)
        }
    }

    fun getTodoByUserId(userId: Long) = repository.getTodoByUserId(userId)
}