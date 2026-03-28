package com.gtbyte.jorgeLanza.home.api

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gtbyte.jorgeLanza.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import androidx.recyclerview.widget.ListAdapter

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
    private val _donuts = MutableStateFlow<List<DonutDb>>(emptyList())
    val donuts: StateFlow<List<DonutDb>> = _donuts

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading



    fun fetchDonuts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _donuts.value = ApiClient.apiService.getDonuts()
            } catch (e: Exception) {
                Log.d("API", "error en ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}

class DonutAdapter : ListAdapter<DonutDb, DonutAdapter.DonutViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DonutDb>() {
            override fun areItemsTheSame(oldItem: DonutDb, newItem: DonutDb): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DonutDb, newItem: DonutDb): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class DonutViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.tvName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.donut_card, parent, false)
        return DonutViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonutViewHolder, position: Int) {
        val donut = getItem(position)
        holder.name.text = donut.name
    }
}