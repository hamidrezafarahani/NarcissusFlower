package com.example.narcissusflower.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.narcissusflower.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

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