package com.example.narcissusflower.ui.plantdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.narcissusflower.R
import com.example.narcissusflower.binding.dataBindings
import com.example.narcissusflower.databinding.FragmentPlantDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantDetailFragment : Fragment(R.layout.fragment_plant_detail) {

    private val binding by dataBindings(FragmentPlantDetailBinding::bind)
    private val viewModel by viewModels<PlantDetailViewModel>()
    private val navController: NavController = findNavController()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            plantDetailViewModel = viewModel
            callback = Callback {
                viewModel.addPlantToGarden()

                // TODO: hide FAB
                // TODO: show snack bar
            }

            galleryNav.setOnClickListener {
                // TODO: Go to gallery fragment
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): PlantDetailFragment {
            return PlantDetailFragment().apply {
                arguments = Bundle().apply {}
            }
        }
    }

    fun interface Callback {

        fun clickEvent()
    }
}