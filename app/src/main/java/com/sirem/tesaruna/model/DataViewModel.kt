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

    val liveDataPost = MutableLiveData<ArrayList<DataNews>>()
    fun getDataPost() {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    dataRepository.getAllDataApi()
                }
                liveDataPost.postValue(result)
            } catch (exception: Exception) {
                Log.d("error courotin",exception.message.toString())
            }

        }

    }

    fun insertData(post: DataNews) {
        dataRepository.insertPost(post)
    }

    fun insertDataAll(post: ArrayList<DataNews>) {
        dataRepository.insertPost(post)
    }

    private val liveDataLocal = MutableLiveData<ArrayList<DataNews>>()
    fun getLisDataLocal(title: String) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    dataRepository.getAllDataLocal(title)
                }
                liveDataLocal.postValue(ArrayList(result))
            } catch (exception: Exception) {
                Log.d("error courotin",exception.message.toString())
            }

        }

    }
}