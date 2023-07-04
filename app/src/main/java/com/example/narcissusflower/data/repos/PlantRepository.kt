package com.example.narcissusflower.data.repos

import com.example.narcissusflower.data.local.dao.PlantDao
import com.example.narcissusflower.data.local.entities.Plant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlantRepository @Inject constructor(
    private val dao: PlantDao
) {

    fun getPlant(plantId: String): Flow<Plant> {
        return dao.getPlant(plantId)
    }

    fun getPlants(): Flow<List<Plant>> {
        return dao.getPlants()
    }

    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int): Flow<List<Plant>> {
        return dao.getPlantsWithGrowZoneNumber(growZoneNumber)
    }
}