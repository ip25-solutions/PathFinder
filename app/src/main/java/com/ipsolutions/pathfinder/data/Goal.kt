package com.ipsolutions.pathfinder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Goal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String = "",
    val progress: Int = 0, // 0-100%
    val deadline: Long? = null, // Fecha límite opcional
    val isCompleted: Boolean = false
)