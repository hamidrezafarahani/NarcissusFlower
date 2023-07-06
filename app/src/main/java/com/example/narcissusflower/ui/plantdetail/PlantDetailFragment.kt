package com.example.narcissusflower.ui.plantdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.narcissusflower.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantDetailFragment : Fragment(R.layout.fragment_plant_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(): PlantDetailFragment {
            return PlantDetailFragment().apply {
                arguments = Bundle().apply {}
            }
        }
    }
}