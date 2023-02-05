package com.example.ilabankdemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ilabankdemo.models.ViewPagerData
import com.example.ilabankdemo.repository.MainRepository
import com.example.ilabankdemo.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository: MainRepository = MainRepository()
    private val _pagerDataList = MutableLiveData<DataState<List<ViewPagerData>>>()
    val pagerDataList: LiveData<DataState<List<ViewPagerData>>>
        get() = _pagerDataList

    init {
        fetchAllItemsData()
    }

    private fun fetchAllItemsData() {
        viewModelScope.launch {
            repository.fetchAllItems()
                .onEach {
                    _pagerDataList.value = it
                }.launchIn(viewModelScope)
        }
    }
}