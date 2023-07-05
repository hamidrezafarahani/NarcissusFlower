package com.example.narcissusflower.ui.plantlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.narcissusflower.R
import com.example.narcissusflower.databinding.FragmentPlantListBinding
import com.example.narcissusflower.utils.dataBindings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantListFragment : Fragment(R.layout.fragment_plant_list) {

    private val binding by dataBindings(FragmentPlantListBinding::bind)
    private val viewModel by viewModels<PlantListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(): PlantListFragment {
            return PlantListFragment().apply {
                arguments = Bundle().apply {}
            }
        }
    }
}
