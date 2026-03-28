package com.gtbyte.jorgeLanza.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtbyte.jorgeLanza.home.data.remote.ByteClient
import com.gtbyte.jorgeLanza.home.data.remote.dto.DonutDto
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _donuts = MutableLiveData<List<DonutDto>>()
    val donuts: LiveData<List<DonutDto>> = _donuts

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun fetchDonuts() {
        viewModelScope.launch {
            _loading.value = true

            try {
                val result = ByteClient.apiService.getDonuts()
                _donuts.value = result

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }
}