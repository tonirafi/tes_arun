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

    val liveDataProdak = MutableLiveData<ArrayList<DataNews>>()
    fun getDataProdukNew() {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    dataRepository.getAllProduk()
                }
                liveDataProdak.postValue(result)
            } catch (exception: Exception) {
//                uiState.value = UiState.Error("Network Request failed")
                Log.d("error courotin",exception.message.toString())
            }

        }

    }

    fun insertData(produk: DataNews) {
        dataRepository.insertPurchased(produk)
    }

    val liveDataPurchased = MutableLiveData<ArrayList<DataNews>>()

    fun getAllPurchased() {
        liveDataPurchased.postValue(dataRepository.getAllPurchased())

    }


//    fun insertProduk(produk: DataNews) {
//        dataRepository.insertDada(produk)
//    }
//
//    fun deleteAllProduk() {
//        dataRepository.deleteAllProdak()
//    }
//
//    val liveDataProdukLocal = MutableLiveData<ArrayList<DataNews>>()
//
//    fun getListProduk(title: String) {
//        liveDataProdukLocal.postValue(produkRepository.getAllProduk("%$title%"))
//
//    }
}