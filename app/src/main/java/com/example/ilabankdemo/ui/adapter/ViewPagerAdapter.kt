package com.example.ilabankdemo.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ilabankdemo.R
import com.example.ilabankdemo.databinding.ViewpagerItemBinding
import com.example.ilabankdemo.models.ViewPagerData

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.PageViewHolder>() {
    private var listItems = arrayListOf<ViewPagerData>()
    @SuppressLint("NotifyDataSetChanged")
    fun setItemData(listItems: List<ViewPagerData>) {
        this.listItems = listItems as ArrayList<ViewPagerData>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val binding = DataBindingUtil.inflate<ViewpagerItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.viewpager_item, parent, false
        )
        return PageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    class PageViewHolder(private val itemBinding: ViewpagerItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ViewPagerData) {
            itemBinding.viewPagerData = item
            itemBinding.executePendingBindings()
        }
    }
}