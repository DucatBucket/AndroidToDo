package com.example.todolist

import android.content.Context
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TaskCellBinding
import com.example.todolist.listeners.TaskClickListener
import com.example.todolist.models.Task
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TaskViewHolder(
    private val context: Context,
    private val binding: TaskCellBinding,
    private val clickListener: TaskClickListener
) : RecyclerView.ViewHolder(binding.root) {
    val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    fun bindTask(task: Task) {
        binding.name.text = task.name

        if (task.complete) {
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.taskCellContainer.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primary_orange))
        } else if (task.dueDate != null) {
            if (task.dueDate!!.isBefore(LocalDate.now())) {
                binding.taskCellContainer.setCardBackgroundColor(ContextCompat.getColor(context, R.color.overdue_red))
            } else if (task.dueTime != null) {
                if (task.dueDate!!.isEqual(LocalDate.now()) && task.dueTime!!.isBefore(LocalTime.now())) {
                    binding.taskCellContainer.setCardBackgroundColor(
                        ContextCompat.getColor(context, R.color.overdue_red)
                    )
                } else {
                    binding.taskCellContainer.setCardBackgroundColor(
                        ContextCompat.getColor(context, R.color.primary_orange)
                    )
                }
            } else {
                binding.taskCellContainer.setCardBackgroundColor(
                    ContextCompat.getColor(context, R.color.primary_orange)
                )
            }
        } else {
            binding.taskCellContainer.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primary_orange))
        }

        binding.completeButton.setImageResource(task.imageResource())

        binding.completeButton.setOnClickListener {
            clickListener.completeTask(task)
        }

        binding.taskCellContainer.setOnClickListener {
            clickListener.editTask(task)
        }

        binding.deleteButton.setOnClickListener {
            clickListener.deleteTask(task)
        }

        if (task.dueTime != null) {
            binding.time.text = timeFormat.format(task.dueTime)
        } else {
            binding.time.text = ""
        }

        if (task.dueDate != null) {
            binding.date.text = "${task.dueDate!!.monthValue}/${task.dueDate!!.dayOfMonth}/${task.dueDate!!.year}"
        } else {
            binding.date.text = ""
        }
    }
}
