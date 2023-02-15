package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TaskCellBinding
import com.example.todolist.listeners.TaskClickListener
import com.example.todolist.models.Task

class TaskAdapter(
    private val tasks: List<Task>,
    private val clickListener: TaskClickListener
) : RecyclerView.Adapter<TaskViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = TaskCellBinding.inflate(from, parent, false)
        return TaskViewHolder(parent.context, binding, clickListener)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindTask(tasks[position])
    }
}