package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.todolist.daos.TaskDao
import com.example.todolist.databases.TaskDatabase
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.models.Task
import com.example.todolist.models.TaskModelFactory
import com.example.todolist.models.TaskViewModel

class MainActivity : AppCompatActivity(), TaskClickListener {
    private lateinit var binding: ActivityMainBinding
    private val taskViewModel: TaskViewModel by viewModels {
        TaskModelFactory((application as TaskApp).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.createTaskButton.setOnClickListener{
            CreateTaskSheet(null).show(supportFragmentManager, "createTaskTag")
        }

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val mainActivity = this
        taskViewModel.taskItems.observe(this){
            binding.todoListRecyclerView.apply{
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskAdapter(it,mainActivity)
            }
        }
    }

    override fun editTask(task: Task) {
        CreateTaskSheet(task).show(supportFragmentManager, "newTaskTag")
    }

    override fun completeTask(task: Task) {
        taskViewModel.setComplete(task)
    }

    private fun taskListSetUp(taskList:ArrayList<Task>,taskDao: TaskDao){

    }
}