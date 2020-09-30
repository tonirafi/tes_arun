package com.sirem.tesaruna.model

import com.sirem.tesaruna.room.DAO
import com.sirem.tesaruna.utils.RestApi
import javax.inject.Inject

class DataRepository @Inject constructor(val dao: DAO, val service: RestApi) {
   suspend fun getAllProduk(): ArrayList<DataNews>{

//        return ArrayList<Data<ResponseHome>>()
        return service?.getDataPost()!!
    }

    fun insertPurchased(data:DataNews) {

//        CoroutineScope().launch {
//            dao.insertProdak(data)

//        }
    }


}