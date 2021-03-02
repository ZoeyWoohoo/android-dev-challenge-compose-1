package com.example.androiddevchallenge.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.App
import com.example.androiddevchallenge.model.CatBean
import com.example.androiddevchallenge.util.JsonUtil
import com.example.androiddevchallenge.util.readAssetsJson
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class CatListViewModel : ViewModel() {

    private var _cats = MutableLiveData(mutableListOf<CatBean>())
    val cats: LiveData<MutableList<CatBean>> = _cats

    private val jsonAdapter: JsonAdapter<List<CatBean>> by lazy {
        JsonUtil.adapterList(CatBean::class)
    }

    fun fetchData() {
        viewModelScope.launch {
            _cats.value = mockData()
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    private suspend fun mockData(): MutableList<CatBean> = withContext(Dispatchers.IO) {
        val cats: MutableList<CatBean> = mutableListOf()
        try {
            val jsonString = App.context.assets.readAssetsJson("cats.json")
            Log.d("TAG", "mockData: json -> $jsonString")
            jsonAdapter.fromJson(jsonString)?.also {
                cats.addAll(it)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return@withContext cats
    }
}