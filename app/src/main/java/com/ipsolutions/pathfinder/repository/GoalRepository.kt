package com.ipsolutions.pathfinder.repository

import com.ipsolutions.pathfinder.data.Goal
import com.ipsolutions.pathfinder.data.GoalDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GoalRepository @Inject constructor(private val dao: GoalDao) {
    val goals: Flow<List<Goal>> = dao.getAllGoals()
    suspend fun addGoal(goal: Goal) = dao.insertGoal(goal)
    suspend fun deleteGoal(goal: Goal) = dao.deleteGoal(goal)
    suspend fun updateGoal(goal: Goal) = dao.updateGoal(goal)
}