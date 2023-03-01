package com.example.list_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var testlist = mutableListOf<String>()
        testlist.add("a")
        testlist.add("b")
        testlist.add("c")

        Log.d("MainActivity",testlist[0])
        Log.d("MainActivity",testlist[1])
        Log.d("MainActivity",testlist[2])
    }
}