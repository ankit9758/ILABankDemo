package com.example.ilabankdemo.repository

import com.example.ilabankdemo.models.ViewPagerData
import com.example.ilabankdemo.utils.DataState
import com.example.ilabankdemo.utils.itemDataList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository {
    fun fetchAllItems(): Flow<DataState<List<ViewPagerData>>> = flow {
        try {
            emit(DataState.Success(itemDataList))
        } catch (e: Exception) {
            emit(DataState.Error(Exception(e.localizedMessage)))
        }
    }.flowOn(Dispatchers.IO)
}