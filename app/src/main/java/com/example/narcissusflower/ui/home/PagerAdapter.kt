package com.example.narcissusflower.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.narcissusflower.ui.garden.GardenFragment
import com.example.narcissusflower.ui.plantlist.PlantListFragment

const val MY_GARDEN_PAGE_INDEX = 0
const val PLANT_LIST_PAGE_INDEX = 1

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentCreator: Map<Int, () -> Fragment> = mapOf(
        MY_GARDEN_PAGE_INDEX to { GardenFragment.newInstance() },
        PLANT_LIST_PAGE_INDEX to { PlantListFragment.newInstance() }
    )

    override fun getItemCount(): Int {
        return tabFragmentCreator.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabFragmentCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}