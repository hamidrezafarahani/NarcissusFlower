package com.example.narcissusflower.ui.plantlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.narcissusflower.R
import com.example.narcissusflower.binding.dataBindings
import com.example.narcissusflower.data.local.entities.Plant
import com.example.narcissusflower.databinding.FragmentPlantListBinding
import com.example.narcissusflower.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlantListFragment : Fragment(R.layout.fragment_plant_list) {

    private val binding by dataBindings(FragmentPlantListBinding::bind)
    private val viewModel by viewModels<PlantListViewModel>()
    private val menuHost: MenuHost = requireActivity()
    private val navController: NavController = findNavController()
    private lateinit var adapter: PlantAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PlantAdapter(viewLifecycleOwner) {
            navigateToPlantDetailFragment(it)
        }
        binding.listPlantList.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.plants.collectLatest {
                    adapter.submitList(it)
                }
            }
        }

        menuHost.addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_plant_list, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.filter_zone -> with(viewModel) {
                        if (isFiltered()) {
                            clearGrowZoneNumber()
                        } else {
                            setGrowZoneNumber(9)
                        }
                        true
                    }

                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun navigateToPlantDetailFragment(plant: Plant) {
        val plantId = plant.plantId
        val direction = HomeFragmentDirections.actionHomeFragmentToPlantDetailFragment(plantId)
        navController.navigate(direction)
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
