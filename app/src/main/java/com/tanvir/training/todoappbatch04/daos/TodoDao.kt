package com.tanvir.training.todoappbatch04.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tanvir.training.todoappbatch04.entities.TodoModel

@Dao
interface TodoDao {
    @Insert
    suspend fun insertTodo(todoModel: TodoModel)

    @Update
    suspend fun updateTodo(todoModel: TodoModel)

    @Delete
    suspend fun deleteTodo(todoModel: TodoModel)

    @Query("select * from tbl_todo where user_id = :userId")
    fun getTodosByUserId(userId: Long) : LiveData<List<TodoModel>>
}