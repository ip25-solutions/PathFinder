package com.ipsolutions.pathfinder.repository

import com.ipsolutions.pathfinder.data.Task
import com.ipsolutions.pathfinder.data.TaskDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(private val dao: TaskDao) {
    val task: Flow<List<Task>> = dao.getAllTasks()
    suspend fun addTask(task: Task) = dao.insertTask(task)
    suspend fun deleteTask(task: Task) = dao.deleteTask(task)
    suspend fun updateTask(task: Task) = dao.updateTask(task)
}