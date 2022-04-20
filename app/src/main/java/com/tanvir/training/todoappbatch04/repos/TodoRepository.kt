package com.tanvir.training.todoappbatch04.repos

import com.tanvir.training.todoappbatch04.daos.TodoDao
import com.tanvir.training.todoappbatch04.entities.TodoModel

class TodoRepository(val todoDao: TodoDao) {

    suspend fun insertTodo(todoModel: TodoModel) {
        todoDao.insertTodo(todoModel)
    }

    fun getTodoByUserId(userId: Long) = todoDao.getTodosByUserId(userId)
}