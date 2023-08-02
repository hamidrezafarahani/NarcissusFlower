package com.example.narcissusflower.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.narcissusflower.data.local.dao.PlantDao
import com.example.narcissusflower.data.local.entities.Plant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltWorker
class SeedDBWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val plantDao: PlantDao
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val file = inputData.getString(KEY_FILENAME)
            if (file != null) {
                applicationContext.assets.open(file).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->

                        val plantType = object : TypeToken<List<Plant>>() {}.type
                        val plants: List<Plant> = Gson().fromJson(jsonReader, plantType)
                        plantDao.insertAll(plants)
                        Result.success()
                    }
                }
            } else {
                Timber.tag(TAG).e("file not exist")
                Result.failure()
            }
        } catch (e: Exception) {
            Timber.tag(TAG).e("something was wrong: ${e.message}")
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDB-worker"
        const val KEY_FILENAME = "PLANT_DATA_FILENAME"
    }
}