package com.example.narcissusflower.ui.garden

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.narcissusflower.R
import com.example.narcissusflower.databinding.FragmentGardenBinding
import com.example.narcissusflower.binding.dataBindings
import com.example.narcissusflower.data.local.entities.PlantAndGardenPlantings
import com.example.narcissusflower.ui.home.HomeFragmentDirections
import com.example.narcissusflower.ui.home.PLANT_LIST_PAGE_INDEX
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GardenFragment : Fragment(R.layout.fragment_garden) {

    private val binding by dataBindings(FragmentGardenBinding::bind)
    private val viewModel by viewModels<GardenViewModel>()
    private lateinit var adapter: GardenPlantingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GardenPlantingAdapter(viewLifecycleOwner) {
            val navController = findNavController()
            navController.navigateToPlantDetailFragment(it)
        }

        binding setupUi adapter
        binding updateUi adapter
    }

    private infix fun FragmentGardenBinding.setupUi(adapter: GardenPlantingAdapter) {
        hasPlantings = false
        listGarden.adapter = adapter
        addPlant.setOnClickListener {
            navigateToPlantListFragment()
        }
    }

    private infix fun FragmentGardenBinding.updateUi(adapter: GardenPlantingAdapter) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.plantAndGardenPlantings.collectLatest {
                    hasPlantings = it.isNotEmpty()
                    adapter.submitList(it)
                }
            }
        }
    }


    private fun navigateToPlantListFragment() {
        // TODO: It's Not Good!
        requireActivity().findViewById<ViewPager2>(R.id.view_pager)
            .currentItem = PLANT_LIST_PAGE_INDEX
    }

    private fun NavController.navigateToPlantDetailFragment(plantings: PlantAndGardenPlantings) {
        val plantId = plantings.plant.plantId
        val direction = HomeFragmentDirections.actionHomeFragmentToPlantDetailFragment(plantId)
        navigate(direction)
    }

    companion object {
        @JvmStatic
        fun newInstance(): GardenFragment {
            return GardenFragment().apply {
                arguments = Bundle().apply {}
            }
        }
    }
}