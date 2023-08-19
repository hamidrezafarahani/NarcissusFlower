package com.example.narcissusflower.ui.garden

import com.example.narcissusflower.data.local.entities.PlantAndGardenPlantings
import java.text.SimpleDateFormat
import java.util.Locale

class PlantAndGardenViewModel(plantings: PlantAndGardenPlantings) {

    private val plant = checkNotNull(plantings.plant)
    private val gardenPlanting = plantings.gardenPlantings[0]


    val plantId: String
        get() = plant.plantId

    val plantName: String
        get() = plant.name

    val imgUrl: String
        get() = plant.imageUrl

    val wateringInterval: String
        get() = plant.wateringInterval.toString()

    val plantDateString: String
        get() = dateFormat.format(gardenPlanting.plantDate.time)

    val waterDateString: String
        get() = dateFormat.format(gardenPlanting.lastWateringDate.time)


    companion object {
        private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
    }
}