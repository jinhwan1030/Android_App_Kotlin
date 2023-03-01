package com.example.listview_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list_item = mutableListOf<ListviewModel>()
        list_item.add(ListviewModel("A","B"))
        list_item.add(ListviewModel("C","D"))
        list_item.add(ListviewModel("E","F"))

        val listview = findViewById<ListView>(R.id.mainlistview)

        val listadapter = ListviewAdapter(list_item)
        listview.adapter = listadapter
    }
}