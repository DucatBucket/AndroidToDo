package com.example.todolist

import androidx.annotation.WorkerThread
import com.example.todolist.daos.TaskDao
import com.example.todolist.models.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: Flow<List<Task>> = taskDao.getAll()

    @WorkerThread
    suspend fun insertTask(task: Task){
        taskDao.insertTask(task)
    }

    @WorkerThread
    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    @WorkerThread
    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }
}