package com.sirem.tesaruna.model

import com.sirem.tesaruna.room.DAO
import com.sirem.tesaruna.utils.RestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DataRepository @Inject constructor(val dao: DAO, val service: RestApi) {
   suspend fun getAllDataApi(): ArrayList<DataNews>{

        return service?.getDataPost()!!
    }

    fun insertPost(data:DataNews) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(data)
        }
    }

    fun insertPost(listData:ArrayList<DataNews>) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(listData)
        }
    }

    fun getAllDataLocal(search:String): List<DataNews> {
        return dao.getAll(search)
    }

}