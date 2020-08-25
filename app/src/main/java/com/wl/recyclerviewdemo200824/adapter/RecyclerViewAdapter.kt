package com.wl.recyclerviewdemo200824.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.wl.recyclerviewdemo200824.R


/**
 * Created by KY5680 on 24,08,2020
 */
class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private var mData: ArrayList<DataModel>
    private var mClickHandler : OnItemClickHandler

    interface OnItemClickHandler{
        fun onItemClick(position : Int, data: DataModel)
        fun onItemRemove(position : Int, data: DataModel)
    }

    constructor(data: ArrayList<DataModel>, clickHandler: OnItemClickHandler) : super() {
        mData = data
        mClickHandler = clickHandler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var cell = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val viewHolder = MyViewHolder(cell)
        viewHolder.icon = cell.findViewById(R.id.icon)
        viewHolder.txtItem = cell.findViewById(R.id.txtItem)
        viewHolder.btnRemove = cell.findViewById(R.id.btnRemove)

        viewHolder.txtItem.setOnClickListener { _ ->
            val position = viewHolder.adapterPosition
            mClickHandler.onItemClick(position, mData[position])
        }

        viewHolder.btnRemove.setOnClickListener { _ ->
            val position = viewHolder.adapterPosition
            mClickHandler.onItemRemove(position, mData[position])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holderMy: MyViewHolder, position: Int) {
        var data = mData[position]

        holderMy.txtItem.text = data.txtItem
        holderMy.icon.setImageResource(data.iconResourceId)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var icon: ImageView
        lateinit var txtItem: TextView
        lateinit var btnRemove: Button

    }

    fun addItem(data: DataModel){
        mData.add(1,data)
        notifyItemInserted(1)
    }

    fun removeItem(position: Int){
        mData.removeAt(position)
        notifyItemRemoved(position)
    }
}

class DataModel {
    var txtItem: String
    var iconResourceId: Int

    constructor(text: String, iconResourceId: Int) {
        this.txtItem = text
        this.iconResourceId = iconResourceId
    }
}


