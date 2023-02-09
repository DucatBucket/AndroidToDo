package com.example.todolist

import android.app.Application
import com.example.todolist.databases.TaskDatabase

class TaskApp:Application() {
    val db by lazy{
        TaskDatabase.getInstance(this)
    }

    val repository by lazy{
        TaskRepository(db.taskDao())
    }
}