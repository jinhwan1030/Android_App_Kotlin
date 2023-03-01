package com.example.todo_list.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import com.example.todo_list.R
import com.example.todo_list.ui.main.components.TodoItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel
){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title ={ Text(text = "오늘 할 일")}
            )
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier =Modifier
                .fillMaxSize()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                value = text,
                onValueChange = {
                    text = it
                },
                placeholder = { Text(text = "할 일")},
                trailingIcon = {
                    Icon(
                       painter = painterResource(id = R.drawable.baseline_add_24), contentDescription =null
                    )
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.addTodo(text)
                    text = ""
                    keyboardController?.hide()
                })
            )

            Divider()

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                items(viewModel.items.value){todoItem ->
                    Column{
                        TodoItem(
                            todo = todoItem,
                            onClick = {todo ->
                                viewModel.toggle(todo.uid)
                            },
                            onDeleteClick = {todo ->
                                viewModel.delete(todo.uid)
                                scope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = "할 일 삭제됨",
                                        actionLabel = "취소"
                                    )
                                    if (result == SnackbarResult.ActionPerformed){
                                        viewModel.restoreTodo()
                                    }
                                }

                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Divider(color = Color.Black, thickness = 1.dp)
                    }
                }
            }
        }
    }
}