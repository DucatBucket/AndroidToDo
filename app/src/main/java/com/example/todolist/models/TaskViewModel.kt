package com.example.todolist.models

import androidx.lifecycle.*
import com.example.todolist.TaskRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.util.*


class TaskViewModel(private val repository: TaskRepository): ViewModel() {
    var taskItems: LiveData<List<Task>> = repository.allTasks.asLiveData()

    fun addTask(task: Task) = viewModelScope.launch{
        repository.insertTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch{
        repository.updateTask(task)
    }

    fun setComplete(task: Task) = viewModelScope.launch{
        task.complete = !task.complete
        repository.updateTask(task)
    }
}

class TaskModelFactory(private val repository: TaskRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown Class used for View Model")
    }
}