package com.example.todolist.models

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolist.R
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Entity
data class Task(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "due_time") var dueTime: LocalTime?,
    @ColumnInfo(name = "due_date") var dueDate: LocalDate?,
    @ColumnInfo(name = "complete") var complete: Boolean = false,
    @PrimaryKey var id: UUID = UUID.randomUUID()
    ) {
    fun imageResource(): Int = if (complete) R.drawable.ic_baseline_check_box_24 else R.drawable.ic_baseline_check_box_outline_blank_24

}