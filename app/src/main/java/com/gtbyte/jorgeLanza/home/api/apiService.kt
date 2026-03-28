package com.gtbyte.jorgeLanza.home.api

import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyApiService {
    @GET("donuts")
    suspend fun getDonuts(): List<DonutDb>
}

object ApiClient {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://private-1ca53c-training45.apiary-mock.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(MyApiService::class.java)
}

class HomeViewModel : ViewModel() {

}