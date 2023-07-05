package com.example.narcissusflower.ui.garden

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GardenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

}