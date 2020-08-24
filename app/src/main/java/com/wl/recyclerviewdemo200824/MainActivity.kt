package com.wl.recyclerviewdemo200824

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mAdapter: ArrayAdapter<String>
    var mData: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..49) {
            mData.add("項目$i")
        }

        mAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData)

        listview.adapter = mAdapter
        listview.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, i, _ ->
                    Toast.makeText(this, "您選了 : " + mData[i], Toast.LENGTH_SHORT)
                            .show()
        }
    }
}