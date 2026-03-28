package com.gtbyte.jorgeLanza.home.presentation.donut.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gtbyte.jorgeLanza.R
import com.gtbyte.jorgeLanza.home.data.remote.dto.DonutDto

class DonutAdapter(private val onClick: (DonutDto) -> Unit) : RecyclerView.Adapter<DonutAdapter.ViewHolder>() {
    private var items: List<DonutDto> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.donut_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val donut = items[position]

        holder.tvTitle.text = donut.name
        holder.tvSubtitle.text = holder.itemView.context
            .getString(R.string.donut_label_tipo, donut.type)
        holder.tvPPU.text = holder.itemView.context
            .getString(R.string.donut_label_ppu, donut.ppu)
        holder.btnDetalle.setOnClickListener {
            onClick(donut)
        }
    }

    override fun getItemCount() = items.size
    fun submitList(newItems: List<DonutDto>) {
        items = newItems
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvSubtitle: TextView = view.findViewById(R.id.tvSubtitle)
        val tvPPU: TextView = view.findViewById(R.id.tvPPU)
        val btnDetalle: TextView = view.findViewById(R.id.btnAction)
    }
}