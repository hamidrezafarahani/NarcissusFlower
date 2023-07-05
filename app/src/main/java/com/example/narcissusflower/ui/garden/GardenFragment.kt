package com.example.narcissusflower.ui.garden

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.narcissusflower.R
import com.example.narcissusflower.databinding.FragmentGardenBinding
import com.example.narcissusflower.utils.dataBindings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GardenFragment : Fragment(R.layout.fragment_garden) {

    private val binding by dataBindings(FragmentGardenBinding::bind)
    private val viewModel by viewModels<GardenViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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