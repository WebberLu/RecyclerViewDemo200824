package com.wl.recyclerviewdemo200824

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wl.recyclerviewdemo200824.adapter.DataModel
import com.wl.recyclerviewdemo200824.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mAdapter: ArrayAdapter<String>
    var mData: ArrayList<String> = ArrayList()
    var mRecyclerViewData: ArrayList<DataModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..49) {
            mData.add("項目$i")
        }

        mAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData)

//        listview.adapter = mAdapter
//        listview.onItemClickListener =
//                AdapterView.OnItemClickListener { _, _, i, _ ->
//                    Toast.makeText(this, "您選了 : " + mData[i], Toast.LENGTH_SHORT)
//                            .show()
//        }

        for (i in 0..49) {
            mRecyclerViewData.add(DataModel("項目$i", R.drawable.ic_launcher_foreground))
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerViewAdapter(context, mRecyclerViewData)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}