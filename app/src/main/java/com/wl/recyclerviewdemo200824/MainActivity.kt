package com.wl.recyclerviewdemo200824

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.wl.recyclerviewdemo200824.adapter.DataModel
import com.wl.recyclerviewdemo200824.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_header.*


class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickHandler {

    lateinit var mAdapter: ArrayAdapter<String>
    var mData: ArrayList<String> = ArrayList()
    var mRecyclerViewData: ArrayList<DataModel> = ArrayList()
    var mViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(mRecyclerViewData, this)

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

            adapter = mViewAdapter

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        btnAdd.setOnClickListener { _ ->
            mViewAdapter.addItem(0, DataModel("new item", R.drawable.ic_launcher_background))
            recycler_view.scrollToPosition(0)

        }

        setDrawerLayout()
        setSupportActionBar(toolbar)
        setActionBarDrawerToggle()
    }

    override fun onItemClick(position: Int, data: DataModel) {
        Toast.makeText(this, "您選了 : " + data.txtItem, Toast.LENGTH_SHORT).show()
    }

    override fun onItemRemove(position: Int, data: DataModel) {
        mViewAdapter.removeItem(position)
        Snackbar
            .make(mainLayout, "移除一個項目 : " + data.txtItem, Snackbar.LENGTH_LONG)
            .setAction("復原") {
                mViewAdapter.addItem(position, data)
                recycler_view.scrollToPosition(position)
            }
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "設定", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_help -> {
                Toast.makeText(this, "使用說明", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_about -> {
                Toast.makeText(this, "關於", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun setDrawerLayout() {

        navigation_view.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            drawerLayout.closeDrawer(GravityCompat.START)

            return@OnNavigationItemSelectedListener when (item.itemId) {
                R.id.action_home -> {
                    Toast.makeText(this@MainActivity, "首頁", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_help -> {
                    Toast.makeText(this@MainActivity, "使用說明", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_about -> {
                    setHeaderInfo()
                    return@OnNavigationItemSelectedListener true
                }
                else -> false
            }
        })
    }

    private fun setActionBarDrawerToggle() {

        var toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState()
    }

    private fun setHeaderInfo() {
        txtHeader.text = "123"
    }
}