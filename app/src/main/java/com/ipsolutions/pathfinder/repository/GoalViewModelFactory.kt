package com.ipsolutions.pathfinder.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ipsolutions.pathfinder.ui.theme.TaskState

class GoalViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskState::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskState(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}