package com.wl.recyclerviewdemo200824.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wl.recyclerviewdemo200824.R


/**
 * Created by KY5680 on 24,08,2020
 */
class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private var mContext: Context
    private var mData: ArrayList<DataModel>

    constructor(context: Context, data: ArrayList<DataModel>) : super() {
        mContext = context
        mData = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var cell = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false)
        val viewHolder = ViewHolder(cell)
        viewHolder.icon = cell.findViewById(R.id.icon)
        viewHolder.txtItem = cell.findViewById(R.id.txtItem)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = mData[position]

        holder.txtItem.text = data.txtItem
        holder.icon.setImageResource(data.iconResourceId)


    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder : RecyclerView.ViewHolder {
        lateinit var icon : ImageView
        lateinit var txtItem: TextView
        constructor(itemView: View) : super(itemView)

    }

}

class DataModel {
    var txtItem:String
    var iconResourceId:Int

    constructor(text: String, iconResourceId: Int) {
        this.txtItem = text
        this.iconResourceId = iconResourceId
    }
}


