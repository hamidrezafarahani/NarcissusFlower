package com.example.narcissusflower.ui.plantdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.narcissusflower.data.repos.GardenPlantingRepository
import com.example.narcissusflower.data.repos.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    private val plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val plantId = savedStateHandle.get<String>(PLANT_ID_SAVED_STATE_KEY)!!

    val isPlanted = gardenPlantingRepository.isPlanted(plantId).stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(), initialValue = false
    )

    val plant = plantRepository.getPlant(plantId).stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(), initialValue = null
    )

    fun addPlantToGarden() {
        viewModelScope.launch {
            gardenPlantingRepository.createGardenPlating(plantId)
        }
    }

    companion object {
        private const val PLANT_ID_SAVED_STATE_KEY = "plantId"
    }
}