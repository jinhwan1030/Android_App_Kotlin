package com.example.todo_list.data.repository

import android.app.Application
import androidx.room.Room
import com.example.todo_list.data.data_source.TodoDatebase
import com.example.todo_list.domain.model.Todo
import com.example.todo_list.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(application: Application): TodoRepository{
    private val db = Room.databaseBuilder(
        application,
        TodoDatebase::class.java,
        "todo-db"
    ).build()

    override fun observeTodos(): Flow<List<Todo>> {
        return db.todoDao().todos()
    }

    override suspend fun addTodo(todo: Todo) {
        return db.todoDao().insert(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        return db.todoDao().update(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        return db.todoDao().delete(todo)
    }
}