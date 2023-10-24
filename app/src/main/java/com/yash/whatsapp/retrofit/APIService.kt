package com.yash.whatsapp.retrofit

import com.yash.whatsapp.model.APIData
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("TestingAdminPanel/api/testing-1")
    suspend fun getData():Response<APIData>
}