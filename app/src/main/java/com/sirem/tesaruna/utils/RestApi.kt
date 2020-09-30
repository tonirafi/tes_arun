package com.sirem.tesaruna.utils


import com.sirem.tesaruna.model.DataNews
import retrofit2.http.GET

interface RestApi {

    @GET("posts")
    suspend fun getDataPost(): ArrayList<DataNews>

}
