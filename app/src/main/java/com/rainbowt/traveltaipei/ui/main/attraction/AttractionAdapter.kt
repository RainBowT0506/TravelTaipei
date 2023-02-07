package com.rainbowt.traveltaipei.ui.main.attraction

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rainbowt.traveltaipei.R
import com.rainbowt.traveltaipei.data.model.Attraction
import com.rainbowt.traveltaipei.data.model.Data
import com.rainbowt.traveltaipei.databinding.ItemAttractionBinding
import com.rainbowt.traveltaipei.ui.base.BaseAdapter


class AttractionAdapter(
    private val context: Context?,
    private val onItemClick: (Data) -> Unit
) : BaseAdapter<Data>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val binding = ItemAttractionBinding.inflate(LayoutInflater.from(context),parent,false)
        return AttractionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val attraction = dataList[position]
        (holder as? AttractionViewHolder)?.bind(attraction)
    }

    override fun getItemCount(): Int {
        return this.dataList.size
    }

    inner class AttractionViewHolder(private val itemBinding: ItemAttractionBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(attraction: Data) {
            itemBinding.model = attraction
            itemBinding.root.setOnClickListener {
                onItemClick.invoke(attraction)
            }
            itemBinding.executePendingBindings()
        }
    }
}