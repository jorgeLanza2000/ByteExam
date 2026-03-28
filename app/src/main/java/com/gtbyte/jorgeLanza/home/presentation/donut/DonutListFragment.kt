package com.gtbyte.jorgeLanza.home.presentation.donut

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gtbyte.jorgeLanza.R
import com.gtbyte.jorgeLanza.home.data.remote.dto.DonutDto
import com.gtbyte.jorgeLanza.home.presentation.HomeViewModel
import com.gtbyte.jorgeLanza.home.presentation.donut.adapter.DonutAdapter

class DonutListFragment : Fragment(R.layout.fragment_donut_list) {

    private val viewModel: HomeViewModel by activityViewModels()

    private lateinit var adapter: DonutAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerDonuts)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        viewModel.donuts.observe(viewLifecycleOwner) { donuts ->
            adapter.submitList(donuts)
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        adapter = DonutAdapter(fun(donut: DonutDto) {
            val fragment = DonutDetailFragment.newInstance(donut)

            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        })
    }
}