package com.example.radiusassigment.api

import com.example.radiusassigment.model.ModelClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("iranjith4/ad-assignment/db")
    fun getData(): Call<ModelClass?>?

}
