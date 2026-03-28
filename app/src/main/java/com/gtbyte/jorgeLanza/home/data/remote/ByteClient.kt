package com.gtbyte.jorgeLanza.home.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ByteClient {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://private-1ca53c-training45.apiary-mock.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: HomeService = retrofit.create(HomeService::class.java)
}