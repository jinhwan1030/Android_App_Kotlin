package com.example.mango_contents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private  val items = mutableListOf<ContentsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items.add(
            ContentsModel("https://www.mangoplate.com/restaurants/9xGdpGAUx-",
                    "https://mp-seoul-image-production-s3.mangoplate.com/195983/1081768_1536757121900_8151?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
            "조연탄"
            )
        )
        items.add(
            ContentsModel("https://www.mangoplate.com/restaurants/9qHLgnJtje",
                "https://mp-seoul-image-production-s3.mangoplate.com/2013877_1640786712428763.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                "영양족발순대국"
            )
        )
        items.add(
            ContentsModel("https://www.mangoplate.com/restaurants/4Vzg7olJy3",
                "https://mp-seoul-image-production-s3.mangoplate.com/200935/90579_1562909555929_16093?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                "원조나주곰탕"
            )
        )
        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/HaBW5K2AMYM3",
                "https://mp-seoul-image-production-s3.mangoplate.com/1111822_1674040918882582.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                "웜테이블"
            )
        )
        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/U4Zq79aPGT5X",
                "https://mp-seoul-image-production-s3.mangoplate.com/856498_1663570445194807.jpg",
                "초심삼겹살"
            )
        )
        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/2LbtRZVu0EJT",
                "https://mp-seoul-image-production-s3.mangoplate.com/383990/45556_1614777611427_10077?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                "마부자생삼겹살"
            )
        )
        val recyclerview = findViewById<RecyclerView>(R.id.rv)
        val rvAdapter = RVAdapter(baseContext,items)
        recyclerview.adapter = rvAdapter

        rvAdapter.itemClick = object : RVAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                var intent = Intent(baseContext, ViewActivity::class.java)
                intent.putExtra("url",items[position].url)
                startActivity(intent)
            }

        }

        // recyclerview.layoutManager = LinearLayoutManager(this)
        // 2열로 표현할 때 GridLayoutManager를 사용
        recyclerview.layoutManager = GridLayoutManager(this,2)

    }
}