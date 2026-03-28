package com.gtbyte.jorgeLanza.home.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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
    private val _users = MutableStateFlow<List<DonutDb>>(emptyList())
    val users: StateFlow<List<DonutDb>> = _users

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _users.value = ApiClient.apiService.getDonuts()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}