package com.example.narcissusflower.data.repos

import com.example.narcissusflower.data.local.dao.GardenPlantingDao
import com.example.narcissusflower.data.local.entities.GardenPlanting
import com.example.narcissusflower.data.local.entities.PlantAndGardenPlantings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GardenPlantingRepository @Inject constructor(
    private val dao: GardenPlantingDao
) {

    fun getPlantedGardens(): Flow<List<PlantAndGardenPlantings>> {
        return dao.getPlantedGardens()
    }

    fun isPlanted(plantId: String): Flow<Boolean> {
        return dao.isPlanted(plantId)
    }

    suspend fun createGardenPlating(plantId: String) {
        dao.insertGardenPlanting(gardenPlanting = GardenPlanting(plantId))
    }

    suspend fun removeGardenPlanting(gardenPlanting: GardenPlanting) {
        dao.deleteGardenPlanting(gardenPlanting = gardenPlanting)
    }
}