package com.example.narcissusflower.ui.plantlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlantListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

}