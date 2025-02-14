package com.ipsolutions.pathfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.ipsolutions.pathfinder.data.AppDatabase
import com.ipsolutions.pathfinder.repository.GoalRepository
import com.ipsolutions.pathfinder.repository.GoalViewModelFactory
import com.ipsolutions.pathfinder.ui.theme.GoalScreen
import com.ipsolutions.pathfinder.ui.theme.GoalViewModel
import com.ipsolutions.pathfinder.ui.theme.PathFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "goal_database"
        ).build()
        val repository = GoalRepository(database.goalDao())
        val viewModelFactory = GoalViewModelFactory(repository)
        setContent {
            PathFinderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: GoalViewModel = viewModel(factory = viewModelFactory)
                    GoalScreen(viewModel)
                }
            }
        }
    }
}