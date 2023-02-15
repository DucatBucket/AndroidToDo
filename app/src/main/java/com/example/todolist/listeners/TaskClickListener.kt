package com.example.todolist.listeners

import com.example.todolist.models.Task

interface TaskClickListener {
    fun editTask(task: Task)
    fun completeTask(task: Task)

    fun deleteTask(task:Task)
}