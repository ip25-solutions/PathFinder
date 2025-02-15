package com.ipsolutions.pathfinder.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ipsolutions.pathfinder.R
import com.ipsolutions.pathfinder.ui.theme.TaskItem
import com.ipsolutions.pathfinder.ui.theme.viewmodel.TaskState
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: TaskState = hiltViewModel()) {
    val tasks by viewModel.task.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar meta")
            }
        },
        content = { padding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                // Imagen de fondo
                Image(
                    painter = painterResource(id = R.drawable.ic_background), // Cambia esto al nombre de tu imagen
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Contenido principal
                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(16.dp) // Ajusta el padding según lo necesites
                ) {
                    Text(
                        text = "Tareas ",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(16.dp)
                    )
                    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                        items(tasks) { goal ->
                            TaskItem(goal, viewModel::updateTaskProgress, viewModel::deleteTask)
                        }
                    }
                }
            }
        }
            )

    if (showDialog) {
        var newGoalTitle by remember { mutableStateOf("") }
        var newGoalDescription by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Nueva Tarea") },
            text = {
                Column {
                    TextField(
                        value = newGoalTitle,
                        onValueChange = { newGoalTitle = it },
                        label = { Text("Título") },
                        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = newGoalDescription,
                        onValueChange = { newGoalDescription = it },
                        label = { Text("Descripción") },
                        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    coroutineScope.launch {
                        viewModel.addTask(newGoalTitle, newGoalDescription)
                    }
                    showDialog = false
                }) {
                    Text("Agregar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
