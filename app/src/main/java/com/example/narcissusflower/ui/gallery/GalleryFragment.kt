package com.example.narcissusflower.ui.gallery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.narcissusflower.R
import com.example.narcissusflower.binding.dataBindings
import com.example.narcissusflower.databinding.FragmentGalleryBinding
import com.example.narcissusflower.extensions.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val binding by dataBindings(FragmentGalleryBinding::bind)
    private val viewModel by viewModels<GalleryViewModel>()
    private val navController = findNavController()
    private val args by navArgs<GalleryFragmentArgs>()
    private var searchJob: Job? = null
    private var adapter by autoCleared<GalleryAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GalleryAdapter {
            val uri = Uri.parse(it.user.attributionUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        binding.photoList.adapter = adapter

        binding.toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }

        search(args.plantName)

        // TODO: impl error handling
        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collectLatest { loadState ->
                    when (loadState.refresh) {
                        is LoadState.Loading -> {}
                        is LoadState.NotLoading -> {}
                        is LoadState.Error -> {}
                    }
                }
            }
        }
    }

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.search(query).collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): GalleryFragment {
            return GalleryFragment().apply {
                arguments = Bundle().apply {}
            }
        }
    }
}