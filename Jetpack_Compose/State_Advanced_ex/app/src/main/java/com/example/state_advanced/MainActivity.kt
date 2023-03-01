package com.example.state_advanced

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.state_advanced.ui.theme.State_advancedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    var text1: MutableState<String> = remember{
        mutableStateOf("Hello World")
    }
    var text2: String by remember{
        mutableStateOf("Hello World")
    }

    val (text:String, setText:(String)->Unit) = remember{
        mutableStateOf("Hello World")
    }

    val text3: State<String> = viewModel.liveData.observeAsState("Hello World")


    Column() {
        Text("Hello World")
        Button(onClick = {
            text1.value = "변경"
            print(text1.value)
            text2 = "변경"
            print(text2)
//            text = "변경"
            setText("변경")

            viewModel.chageValue("변경")
        }) {
            Text("클릭")
        }
        TextField(value = text, onValueChange = setText)
    }
}

class MainViewModel : ViewModel(){
    private val _value = mutableStateOf("Hello World")
    val value: State<String> = _value

    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> = _liveData

    fun chageValue(value: String){
        _value.value = value
    }
}