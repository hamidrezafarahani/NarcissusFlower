package com.example.narcissusflower.ui.plantlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.narcissusflower.data.local.entities.Plant
import com.example.narcissusflower.data.repos.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantListViewModel @Inject constructor(
    private val repository: PlantRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        viewModelScope.launch {
            growZone.collect {
                savedStateHandle[GROW_ZONE_SAVED_STATE_KEY] = it
            }
        }
    }

    private val growZone: MutableStateFlow<Int> = MutableStateFlow(
        savedStateHandle[GROW_ZONE_SAVED_STATE_KEY] ?: NO_GROW_ZONE
    )

    fun setGrowZoneNumber(zone: Int) {
        growZone.value = zone
    }

    fun clearGrowZoneNumber() {
        growZone.value = NO_GROW_ZONE
    }

    fun isFiltered(): Boolean {
        return growZone.value != NO_GROW_ZONE
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _plants: Flow<List<Plant>> = growZone.flatMapLatest {
        if (it == NO_GROW_ZONE) {
            repository.getPlants()
        } else {
            repository.getPlantsWithGrowZoneNumber(it)
        }
    }

    val plants: SharedFlow<List<Plant>>
        get() = _plants.shareIn(
            scope = viewModelScope, started = SharingStarted.WhileSubscribed(), replay = 1
        )

    companion object {

        private const val NO_GROW_ZONE = -1
        private const val GROW_ZONE_SAVED_STATE_KEY = "GROW_ZONE_SAVED_STATE_KEY"
    }
}