package com.ipsolutions.pathfinder.ui.theme

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipsolutions.pathfinder.data.Goal
import com.ipsolutions.pathfinder.repository.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(private val repository: GoalRepository) : ViewModel() {
    private val _goals = MutableStateFlow<List<Goal>>(emptyList())
    val goals: StateFlow<List<Goal>> = _goals

    init {
        viewModelScope.launch {
            repository.goals.collect { _goals.value = it }
        }
    }

    fun addGoal(title: String, description: String) {
        viewModelScope.launch { repository.addGoal(Goal(title = title, description = description)) }
    }

    fun deleteGoal(goal: Goal) {
        viewModelScope.launch { repository.deleteGoal(goal) }
    }

    fun updateGoalProgress(goal: Goal, progress: Int) {
        viewModelScope.launch { repository.updateGoal(goal.copy(progress = progress)) }
    }
}
