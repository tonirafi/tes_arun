package com.sirem.tesaruna.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataViewModel @Inject constructor(val application: Application, val dataRepository: DataRepository) : ViewModel() {

    val liveDataPostApi = MutableLiveData<ArrayList<DataNews>>()
    fun getDataPost() {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    dataRepository.getAllDataApi()
                }
                liveDataPostApi.postValue(result)
            } catch (exception: Exception) {
                Log.d("error courotin",exception.message.toString())
            }

        }

    }

    fun insertDataAll(post: ArrayList<DataNews>) {
        dataRepository.insertPost(post)
    }

    val liveDataLocal = MutableLiveData<ArrayList<DataNews>>()
    fun getLisDataLocal(title: String) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    dataRepository.getAllDataLocal("%$title%")
                }
                liveDataLocal.postValue(ArrayList(result))
            } catch (exception: Exception) {
                Log.d("error courotin",exception.message.toString())
            }

        }

    }
}