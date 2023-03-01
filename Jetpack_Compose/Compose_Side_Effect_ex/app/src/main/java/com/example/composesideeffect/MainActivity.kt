package com.example.composesideeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composesideeffect.ui.theme.ComposesideeffectTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val (text,setValue) = androidx.compose.runtime.remember {
                androidx.compose.runtime.mutableStateOf("")
            }

            val scaffoldState = androidx.compose.material.rememberScaffoldState()
            val scope = androidx.compose.runtime.rememberCoroutineScope()
            val keyboardController = androidx.compose.ui.platform.LocalSoftwareKeyboardController.current

            androidx.compose.material.Scaffold(
                scaffoldState = scaffoldState
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    TextField(
                        value = text,
                        onValueChange = setValue
                    )
                    Button(onClick = {
                        keyboardController?.hide()
                        scope.launch {
                        //snackbar를 사용할 때 scaffoldState가 필요
                            scaffoldState.snackbarHostState.showSnackbar("Hello $text")
                        }
                    }) {
                        Text(text = "클릭!!")
                    }
                }
            }
        }
    }
}
