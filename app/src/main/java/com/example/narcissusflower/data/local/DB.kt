package com.example.narcissusflower.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.narcissusflower.data.local.dao.GardenPlantingDao
import com.example.narcissusflower.data.local.dao.PlantDao
import com.example.narcissusflower.data.local.entities.GardenPlanting
import com.example.narcissusflower.data.local.entities.Plant

@Database(
    entities = [Plant::class, GardenPlanting::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class DB : RoomDatabase() {

    abstract fun plantDao(): PlantDao

    abstract fun gardenPlantingDao(): GardenPlantingDao
}

