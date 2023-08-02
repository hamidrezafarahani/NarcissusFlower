package com.example.narcissusflower.ui.plantdetail

import android.content.Intent
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        with(binding) {
            plantDetailViewModel = viewModel
            callback = Callback {

                viewModel.addPlantToGarden()
                hideAppbarFab(fab)
                Snackbar.make(root, "Added to garden", Snackbar.LENGTH_LONG).show()
            }

            galleryNav.setOnClickListener {

                viewModel.plant.value?.let { plant ->
                    navController.navigateToGalleryFragment(plant)
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

            toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_share -> {
                        createShareIntent()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun hideAppbarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }

    private fun NavController.navigateToGalleryFragment(plant: Plant) {
        val plantName = plant.name
        val direction = PlantDetailFragmentDirections
            .actionPlantDetailFragmentToGalleryFragment(plantName)
        navigate(direction)
    }

    private fun createShareIntent() {
        val shareText = viewModel.plant.value.let { plant ->
            if (plant == null) "" else "Check out the $plant plant in the Android NarcissusFlower app"
        }
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        val shareIntent = Intent.createChooser(sendIntent, null).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        }
        startActivity(shareIntent)
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