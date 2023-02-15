package com.example.todolist

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.FragmentCreateTaskSheetBinding
import com.example.todolist.models.Task
import com.example.todolist.models.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDate
import java.time.LocalTime

class CreateTaskSheet(var task: Task?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCreateTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel
    private var dueTime: LocalTime? = null
    private var dueDate: LocalDate? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (task != null) {
            binding.title.text = "Edit Task"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(task!!.name)
            if (task!!.dueTime != null) {
                dueTime = task!!.dueTime!!
                updateTimeButtonText()
            }
            if (task!!.dueDate != null) {
                dueDate = task!!.dueDate!!
                updateDateButtonText()
            }
        } else {
            binding.title.text = "Create A New Task"
        }

        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }

        binding.setTime.setOnClickListener {
            openTimePicker()
        }

        binding.setDate.setOnClickListener {
            openDatePicker()
        }
    }

    private fun openTimePicker() {
        if (dueTime == null) {
            dueTime = LocalTime.now()
        }
        val listener = TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
            dueTime = LocalTime.of(selectedHour, selectedMinute)
            updateTimeButtonText()
        }
        val dialog = TimePickerDialog(activity, listener, 23, 59, true)
        dialog.setTitle("Time Due")
        dialog.show()
    }

    private fun openDatePicker() {
        if (dueDate == null) {
            dueDate = LocalDate.now()
        }

        val listener = DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
            dueDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
            updateDateButtonText()
        }
        val dialog = activity?.let {
            DatePickerDialog(
                it,
                listener,
                dueDate!!.year,
                dueDate!!.monthValue - 1,
                dueDate!!.dayOfMonth
            )
        }
        dialog!!.setTitle("Date Due")
        dialog.show()
    }

    private fun updateTimeButtonText() {
        binding.setTime.text = dueTime!!.toString()
    }

    private fun updateDateButtonText() {
        binding.setDate.text = dueDate!!.month.toString() + "/" + dueDate!!.dayOfMonth.toString() + "/" + dueDate!!.year.toString()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCreateTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun saveAction() {
        val name = binding.name.text.toString()

        if (name.isEmpty() || dueTime == null || dueDate == null) {
            return
        }

        if (task == null) {
            val newTask = Task(name, dueTime, dueDate)
            taskViewModel.addTask(newTask)
        } else {
            task!!.name = name
            task!!.dueTime = dueTime
            task!!.dueDate = dueDate
            taskViewModel.updateTask(task!!)
        }

        binding.name.setText("")
        dismiss()
    }
}
