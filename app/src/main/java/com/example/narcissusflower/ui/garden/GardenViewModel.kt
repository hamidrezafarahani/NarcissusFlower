package com.example.narcissusflower.ui.garden

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.narcissusflower.data.local.entities.PlantAndGardenPlantings
import com.example.narcissusflower.domain.GetPlantAndGardenPlantingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GardenViewModel @Inject constructor(
    private val getPlantAndGardenPlantings: GetPlantAndGardenPlantingsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val plantAndGardenPlantings: SharedFlow<List<PlantAndGardenPlantings>>
        get() = getPlantAndGardenPlantings(viewModelScope) {
            Timber.tag(TAG).d(it)
        }

    companion object {
        private const val TAG = "GARDEN_VIEW_MODEL_ERROR_USE_CASE"
    }
}