package com.example.ilabankdemo.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.ilabankdemo.R
import com.example.ilabankdemo.databinding.ActivityMainBinding
import com.example.ilabankdemo.models.ItemData
import com.example.ilabankdemo.models.ViewPagerData
import com.example.ilabankdemo.ui.adapter.MainAdapter
import com.example.ilabankdemo.ui.adapter.ViewPagerAdapter
import com.example.ilabankdemo.utils.DataState
import com.example.ilabankdemo.utils.showToast
import com.example.ilabankdemo.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private var originalPagerList = arrayListOf<ViewPagerData>()
    private var originalItemList = arrayListOf<ItemData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupViews()
        setPagerListener()
        setSearchListener()
        setupObservers()
    }

    private fun setSearchListener() {
        val itemList = arrayListOf<ItemData>()
        binding.searchView.setOnCloseListener {
            clearSearch()
            mainAdapter.setItemList(originalItemList)
            false
        }

        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                itemList.clear()
                if (originalItemList.isNotEmpty()) {
                    val data = originalItemList.filter { item: ItemData ->
                        item.name?.lowercase().let {
                            it!!.contains(query.lowercase())
                        }
                    }
                    itemList.addAll(data)
                    mainAdapter.setItemList(itemList)
                }

                return false
            }

        })
    }

    private fun setPagerListener() {
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("Selected_Page", position.toString())

                val listItemData = originalPagerList[position].listItems
                originalItemList=listItemData
                mainAdapter.setItemList(listItemData)

                //Clear the search when  pager swipe to next item.
                clearSearch()
            }
        })
    }


    private fun setupViews() {
        //set recyclerviewData
        mainAdapter = MainAdapter()
        binding.rvItem.apply {
            addItemDecoration(DividerItemDecoration(this@MainActivity ,LinearLayout.VERTICAL))
            adapter = mainAdapter
        }

        //set Pager data
        viewPagerAdapter = ViewPagerAdapter()
        binding.viewPager.apply {
            adapter = viewPagerAdapter
        }
        // pager indicator
        TabLayoutMediator(binding.intoTabLayout, binding.viewPager) { _, _ ->
        }.attach()



    }
    private fun setupObservers() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.pagerDataList.observe(this@MainActivity) {
                when(it){
                    is DataState.Loading->{}
                    is DataState.Success->{
                        originalPagerList.addAll(it.data)
                        viewPagerAdapter.setItemData(originalPagerList)
                        originalItemList=originalPagerList.first().listItems
                        mainAdapter.setItemList(originalItemList)  //Set First Data to the Item list
                    }
                    is DataState.Error->{
                        showToast(it.exception.toString())
                    }

                    else -> {

                    }
                }
            }
        }
    }


    private fun clearSearch() {
        binding.searchView.setQuery("", true)
        binding.searchView.clearFocus()
    }
}