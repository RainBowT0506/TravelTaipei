package com.rainbowt.traveltaipei.ui.base

import androidx.recyclerview.widget.RecyclerView

/**
 * @Author RainBowT
 */
abstract class BaseAdapter<B> : RecyclerView.Adapter<RecyclerView.ViewHolder>(), CRUD<B> {
    var dataList = mutableListOf<B>()
        protected set

    override fun getItemCount(): Int = dataList.size

    override fun set(position: Int, data: B) {
        dataList[position] = data
        notifyItemChanged(position)
    }

    override fun remove(pos: Int) {
        dataList.removeAt(pos)
        notifyItemRemoved(pos)
        notifyItemRangeChanged(0, dataList.size)
    }

    override fun setData(dataList: MutableList<B>?) {
        this.dataList.clear()
        this.dataList.addAll(dataList!!.toMutableList())
        notifyDataSetChanged()
    }

    override fun add(position: Int, data: B) {
        dataList.add(position,data)
        notifyItemInserted(position)
        notifyItemRangeChanged(0,dataList.size)
    }

    override fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }
}