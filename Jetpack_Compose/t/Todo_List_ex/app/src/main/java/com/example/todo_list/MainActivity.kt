package com.example.todo_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo_list.domain.util.TodoAndroidViewModelFactory
import com.example.todo_list.ui.main.MainScreen
import com.example.todo_list.ui.main.MainViewModel
import com.example.todo_list.ui.theme.Todo_listTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = viewModel(
                factory = TodoAndroidViewModelFactory(application))
            MainScreen(viewModel = viewModel)
        }
    }
}
