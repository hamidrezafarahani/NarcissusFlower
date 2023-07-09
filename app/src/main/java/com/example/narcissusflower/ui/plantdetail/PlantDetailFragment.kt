package com.example.narcissusflower.ui.plantdetail

import android.os.Bundle
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.narcissusflower.R
import com.example.narcissusflower.binding.dataBindings
import com.example.narcissusflower.data.local.entities.Plant
import com.example.narcissusflower.databinding.FragmentPlantDetailBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
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
                hideAppbarFab(fab)
                Snackbar.make(root, "Added to garden", Snackbar.LENGTH_LONG).show()
            }

            galleryNav.setOnClickListener {

                viewModel.plant.value?.let { plant ->
                    navigateToGalleryFragment(plant)
                }
            }

            var isToolbarShown = false
            plantDetailScrollview.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
                    val shouldShowToolbar = scrollY > toolbar.height
                    if (isToolbarShown != shouldShowToolbar) {
                        isToolbarShown = shouldShowToolbar
                        appbar.isActivated = shouldShowToolbar
                        toolbarLayout.isTitleEnabled = shouldShowToolbar
                    }
                }
            )

            toolbar.setNavigationOnClickListener {
                navController.navigateUp()
            }
        }
    }


    private fun hideAppbarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }

    private fun navigateToGalleryFragment(plant: Plant) {
        val plantName = plant.name
        val direction = PlantDetailFragmentDirections
            .actionPlantDetailFragmentToGalleryFragment(plantName)
        navController.navigate(direction)
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