package com.example.narcissusflower.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.narcissusflower.data.local.entities.GardenPlanting
import com.example.narcissusflower.data.local.entities.PlantAndGardenPlantings
import kotlinx.coroutines.flow.Flow

@Dao
interface GardenPlantingDao {

    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings(): Flow<List<GardenPlanting>>

    @Query("SELECT EXISTS (SELECT 1 FROM garden_plantings WHERE plant_id = :plantId LIMIT 1)")
    fun isPlanted(plantId: String): Flow<Boolean>

    @Transaction
    @Query("SELECT * FROM plants WHERE id IN (SELECT DISTINCT(plant_id) FROM garden_plantings)")
    fun getPlantedGardens(): Flow<List<PlantAndGardenPlantings>>

    @Insert
    suspend fun insertGardenPlanting(gardenPlanting: GardenPlanting): Long

    @Delete
    suspend fun deleteGardenPlanting(gardenPlanting: GardenPlanting)
}