package com.example.ilabankdemo.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ilabankdemo.R
import com.example.ilabankdemo.databinding.ListItemBinding
import com.example.ilabankdemo.models.ItemData


class MainAdapter : RecyclerView.Adapter<MainAdapter.ItemViewHolder>() {
    private var listItems = arrayListOf<ItemData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(data: List<ItemData>) {
        this.listItems = data as ArrayList<ItemData>
        notifyDataSetChanged()
    }

    class ItemViewHolder(private val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ItemData) {
            itemBinding.itemData = item
            itemBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = DataBindingUtil.inflate<ListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item,parent,false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listItems[position])
    }
}