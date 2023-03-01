package com.example.d_day

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startBtn)
        val endButton = findViewById<Button>(R.id.endBtn)

        var startDate = ""
        var endDate = ""

        val calendar_start = Calendar.getInstance()
        val calendar_end = Calendar.getInstance()

        startButton.setOnClickListener {
            val today = GregorianCalendar()
            val year = today.get(Calendar.YEAR)
            val month = today.get(Calendar.MONTH)
            val day = today.get(Calendar.DATE)
            val dialog = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                    //startDate = "${p1} + ${p2+1} + ${p3}"
                    startDate = p1.toString() + (p2+1).toString() + p3.toString()

                    calendar_start.set(p1,p2+1,p3)
                    Log.d("day",startDate)
                }

            },year,month,day)
            dialog.show()
        }

        endButton.setOnClickListener {
            val today = GregorianCalendar()
            val year = today.get(Calendar.YEAR)
            val month = today.get(Calendar.MONTH)
            val day = today.get(Calendar.DATE)
            val dialog = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                    //startDate = "${p1} + ${p2+1} + ${p3}"
                    endDate = p1.toString() + (p2+1).toString() + p3.toString()
                    calendar_end.set(p1,p2+1,p3)

                    val finalData = TimeUnit.MILLISECONDS.toDays(calendar_end.timeInMillis - calendar_start.timeInMillis)

                    Log.d("day",endDate)

                    val textArea = findViewById<TextView>(R.id.finalDate)

                    textArea.setText(finalData.toString())
                }

            },year,month,day)
            dialog.show()

        }
    }
}