package com.example.listview_2nd_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val list_item = mutableListOf<String>()

        // list_item.add("A")
        // list_item.add("B")
        // list_item.add("C")

        val list_item2 = mutableListOf<ListViewModel>()
        list_item2.add(ListViewModel("a","b"))
        list_item2.add(ListViewModel("c","d"))
        list_item2.add(ListViewModel("e","f"))

        val listview = findViewById<ListView>(R.id.mainlistview)

        val list_adapter = ListViewAdpater(list_item2)

        listview.adapter = list_adapter

        listview.setOnItemClickListener { p2, view, p0, l ->
            Toast.makeText(this,list_item2[p0].text1, Toast.LENGTH_LONG).show()
        }


    }
}