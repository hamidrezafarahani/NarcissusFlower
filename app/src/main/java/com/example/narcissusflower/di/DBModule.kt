package com.example.narcissusflower.di

import android.content.Context
import androidx.room.Room
import com.example.narcissusflower.data.local.DB
import com.example.narcissusflower.data.local.dao.GardenPlantingDao
import com.example.narcissusflower.data.local.dao.PlantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): DB {
        return Room.databaseBuilder(
            context, DB::class.java, "narcissus.db"
        ).build()
    }

    @Provides
    fun providePlantDao(db: DB): PlantDao {
        return db.plantDao()
    }

    @Provides
    fun provideGardenPlantingDao(db: DB): GardenPlantingDao {
        return db.gardenPlantingDao()
    }
}