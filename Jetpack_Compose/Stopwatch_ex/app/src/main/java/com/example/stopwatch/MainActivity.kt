package com.example.stopwatch

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stopwatch.ui.theme.StopwatchTheme
import java.util.*
import kotlin.concurrent.timer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>()

            val sec = viewModel.sec.value
            val millisec = viewModel.millisec.value
            val isRunning = viewModel.isRunning.value
            val lapTimes = viewModel.lapTimes.value

            MainScreen(
                sec = sec,
                millisec = millisec,
                isRunning = isRunning,
                lapTimes = lapTimes,
                onReset = { viewModel.reset() },
                onToggle = {running ->
                    if (running){
                        viewModel.pause()
                    }else{
                        viewModel.start()
                    }
                },
                onLapTime = {viewModel.recordLapTime()}
            )
        }
    }
}

class MainViewModel : ViewModel(){

    private var time = 0
    private var timerTask : Timer? = null

    private val _isRunning = mutableStateOf(false)
    val isRunning: State<Boolean> = _isRunning

    private val _sec = mutableStateOf(0)
    val sec: State<Int> = _sec

    private val _millisec = mutableStateOf(0)
    val millisec: State<Int> = _millisec

    private val _labTimes = mutableStateOf(mutableListOf<String>())
    val lapTimes: State<List<String>> = _labTimes

    private var lap = 1

    fun start() {
        _isRunning.value = true
        timerTask = timer(period = 10){
            time++
            _sec.value = time / 100
            _millisec.value = time % 100
        }
    }

    fun pause(){
        _isRunning.value = false
        timerTask?.cancel()
    }

    fun reset(){
        timerTask?.cancel()

        time = 0
        _isRunning.value = false
        _sec.value = 0
        _millisec.value = 0

        _labTimes.value.clear()
        lap = 1
    }

    fun recordLapTime(){
        _labTimes.value.add(0, "$lap LAP : ${sec.value}.${millisec.value}")
        lap++
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    sec: Int,
    millisec: Int,
    isRunning: Boolean,
    lapTimes: List<String>,
    onReset: () -> Unit,
    onToggle: (Boolean)-> Unit,
    onLapTime: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(title = {Text(text = "StopWatch")})
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(40.dp))
            
            Row (
                verticalAlignment = Alignment.Bottom
            ){
                Text(text = "$sec", fontSize = 100.sp)
                Text(text = "$millisec")
                
            }
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                lapTimes.forEach{lapTime ->
                    Text(lapTime)
                }
            }

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
                ) {
                    FloatingActionButton(
                        onClick = { onReset() },
                        backgroundColor = Color.Red) {
                        Image(painter = painterResource(id = R.drawable.baseline_refresh_24), contentDescription = "reset" )
                    }
                    FloatingActionButton(
                        onClick = { onToggle(isRunning) },
                        backgroundColor = Color.Green) {
                        Image(painter = painterResource(
                            id = if(isRunning) R.drawable.baseline_pause_circle_filled_24
                        else R.drawable.baseline_play_arrow_24
                        ), contentDescription = "start/pause" )
                    }
            Button(onClick = { onLapTime()}) {
                Text(text = "랩 타임")
            }
            }
        }

    }
}