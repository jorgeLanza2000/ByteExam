package com.gtbyte.jorgeLanza.home.data.remote

import com.gtbyte.jorgeLanza.home.data.remote.dto.DonutDto
import retrofit2.http.GET

interface HomeService {
    @GET("donuts")
    suspend fun getDonuts(): List<DonutDto>
}