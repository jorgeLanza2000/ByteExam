package com.gtbyte.jorgeLanza.home.presentation.donut

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.gtbyte.jorgeLanza.R
import com.gtbyte.jorgeLanza.home.data.remote.dto.DonutDto

class DonutDetailFragment : Fragment(R.layout.fragment_donut_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<MaterialButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvPpu = view.findViewById<TextView>(R.id.tvPpu)
        val layoutBatters = view.findViewById<LinearLayout>(R.id.layoutBatters)
        val layoutToppings = view.findViewById<LinearLayout>(R.id.layoutToppings)

        val name = arguments?.getString("name") ?: ""
        val ppu = arguments?.getDouble("ppu") ?: 0.0
        val batters = arguments?.getStringArrayList("batters") ?: arrayListOf()
        val toppings = arguments?.getStringArrayList("toppings") ?: arrayListOf()

        tvName.text = name
        tvPpu.text = getString(R.string.donut_label_ppu, ppu)

        batters.forEach {
            val textView = TextView(requireContext())
            textView.text = getString(R.string.donut_label_list_detail, it)
            layoutBatters.addView(textView)
        }

        toppings.forEach {
            val textView = TextView(requireContext())
            textView.text = getString(R.string.donut_label_list_detail, it)
            layoutToppings.addView(textView)
        }
    }

    companion object {
        fun newInstance(donut: DonutDto): DonutDetailFragment {
            val fragment = DonutDetailFragment()
            val bundle = Bundle()

            bundle.putString("name", donut.name)
            bundle.putDouble("ppu", donut.ppu)

            val battersList = ArrayList<String>()
            donut.batters.batter.forEach {
                battersList.add(it.type)
            }

            val toppingsList = ArrayList<String>()
            donut.topping.forEach {
                toppingsList.add(it.type)
            }

            bundle.putStringArrayList("batters", battersList)
            bundle.putStringArrayList("toppings", toppingsList)

            fragment.arguments = bundle
            return fragment
        }
    }
}