package com.example.narcissusflower.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.narcissusflower.R
import com.example.narcissusflower.binding.dataBindings
import com.example.narcissusflower.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by dataBindings(FragmentHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewPager.adapter = PagerAdapter(this@HomeFragment)

            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.setIcon(getTabIcon(position))
                tab.text = getTabTitle(position)
            }.attach()

            (activity as AppCompatActivity).setSupportActionBar(toolbar)
        }
    }

    private fun getTabIcon(position: Int): Int = when (position) {
        MY_GARDEN_PAGE_INDEX -> R.drawable.garden_tab_selector
        PLANT_LIST_PAGE_INDEX -> R.drawable.plant_list_tab_selector
        else -> throw IndexOutOfBoundsException()
    }

    private fun getTabTitle(position: Int): String? = when (position) {
        MY_GARDEN_PAGE_INDEX -> "My Garden"
        PLANT_LIST_PAGE_INDEX -> "Plant List"
        else -> null
    }
}