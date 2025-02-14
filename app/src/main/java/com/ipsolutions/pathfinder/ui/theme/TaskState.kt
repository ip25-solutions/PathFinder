package com.ipsolutions.pathfinder.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipsolutions.pathfinder.data.Task
import com.ipsolutions.pathfinder.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskState @Inject constructor(private val repository: TaskRepository) : ViewModel() {
    private val _task = MutableStateFlow<List<Task>>(emptyList())
    val task: StateFlow<List<Task>> = _task

    init {
        viewModelScope.launch {
            repository.task.collect { _task.value = it }
        }
    }

    fun addTask(title: String, description: String) {
        viewModelScope.launch { repository.addTask(Task(title = title, description = description)) }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch { repository.deleteTask(task) }
    }

    fun updateTaskProgress(task: Task, progress: Int) {
        viewModelScope.launch { repository.updateTask(task.copy(progress = progress)) }
    }
}
