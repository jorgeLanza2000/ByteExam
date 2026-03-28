package com.gtbyte.jorgeLanza.home.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.gtbyte.jorgeLanza.R
import kotlinx.coroutines.launch
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
    private val _donuts = MutableLiveData<List<DonutDb>>()
    val donuts: LiveData<List<DonutDb>> = _donuts

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun fetchDonuts() {
        viewModelScope.launch {
            _loading.value = true

            try {
                val result = ApiClient.apiService.getDonuts()
                _donuts.value = result

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }
}

class DonutAdapter : RecyclerView.Adapter<DonutAdapter.ViewHolder>() {

    private var items: List<DonutDb> = emptyList()

    fun submitList(newItems: List<DonutDb>) {
        items = newItems
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.donut_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val donut = items[position]
        holder.tvName.text = donut.name // ajusta según tu modelo
    }

    override fun getItemCount() = items.size
}