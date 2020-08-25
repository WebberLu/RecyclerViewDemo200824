package com.wl.recyclerviewdemo200824

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wl.recyclerviewdemo200824.adapter.DataModel
import com.wl.recyclerviewdemo200824.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.OnItemClickHandler {

    lateinit var mAdapter: ArrayAdapter<String>
    var mData: ArrayList<String> = ArrayList()
    var mRecyclerViewData: ArrayList<DataModel> = ArrayList()
    var viewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(mRecyclerViewData, this)

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
            //type 1
            layoutManager = LinearLayoutManager(context)
            //type 2
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            //type 3
//            layoutManager = GridLayoutManager(context,2)

            adapter = viewAdapter

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        btnAdd.setOnClickListener { _-> viewAdapter.addItem(DataModel("new item", R.drawable.ic_launcher_background))}
    }

    override fun onItemClick(position: Int, data: DataModel) {
        Toast.makeText(this, "您選了 : " + data.txtItem, Toast.LENGTH_SHORT).show()
    }

    override fun onItemRemove(position: Int, data: DataModel) {
        viewAdapter.removeItem(position)
        Toast.makeText(this, "remove : " + data.txtItem, Toast.LENGTH_SHORT).show()
    }
}