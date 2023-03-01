package com.example.listview_ex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class ListviewAdapter(val List : MutableList<ListviewModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return List.size
    }

    override fun getItem(p0: Int): Any {
        return List[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var convertView = p1

        if(convertView == null){
            convertView = LayoutInflater.from(p2?.context).inflate(R.layout.listview_item,p2,false)
        }

        val title=convertView!!.findViewById<TextView>(R.id.listviewItem1)
        val content = convertView!!.findViewById<TextView>(R.id.listviewItem2)
        title.text=List[p0].title
        content.text = List[p0].content
        // List[0] -> ListViewModel("A","B")
        // List[1] -> ListVierModel("C","D")
        // List[2] -> ListVierModel("E","F")
        return convertView!!
    }
}