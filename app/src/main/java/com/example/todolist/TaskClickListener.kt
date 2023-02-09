package com.example.todolist

import com.example.todolist.models.Task

interface TaskClickListener {
    fun editTask(task: Task)
    fun completeTask(task: Task)
}