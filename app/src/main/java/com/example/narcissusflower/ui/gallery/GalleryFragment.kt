package com.example.narcissusflower.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.narcissusflower.R
import com.example.narcissusflower.binding.dataBindings
import com.example.narcissusflower.databinding.FragmentGardenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val binding by dataBindings(FragmentGardenBinding::bind)
    private val viewModel by viewModels<GalleryViewModel>()
    private var searchJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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