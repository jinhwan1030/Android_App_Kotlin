package com.example.todo_list.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo_list.domain.model.Todo

@Database(entities=[Todo::class], version = 1)
abstract class TodoDatebase : RoomDatabase(){
    abstract fun todoDao(): TodoDao
}