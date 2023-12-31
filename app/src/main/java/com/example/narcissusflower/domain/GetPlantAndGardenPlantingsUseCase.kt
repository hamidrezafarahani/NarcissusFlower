package com.example.narcissusflower.domain

import com.example.narcissusflower.data.local.entities.PlantAndGardenPlantings
import com.example.narcissusflower.data.repos.GardenPlantingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class GetPlantAndGardenPlantingsUseCase @Inject constructor(
    private val repository: GardenPlantingRepository
) {

    operator fun invoke(
        coroutineScope: CoroutineScope,
        op: (Throwable) -> Unit
    ): SharedFlow<List<PlantAndGardenPlantings>> {
        return repository.getPlantedGardens()
            .catch { op(it) }
            .shareIn(
                scope = coroutineScope, started = SharingStarted.WhileSubscribed(), replay = 1
            )
    }
}