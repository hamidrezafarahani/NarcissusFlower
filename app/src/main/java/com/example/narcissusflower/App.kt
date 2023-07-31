package com.example.narcissusflower

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import androidx.work.workDataOf
import com.example.narcissusflower.tasks.SeedDBWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

private const val PLANT_DATA_FILE = "plants.json"

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var feedWorkerFactory: WorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(feedWorkerFactory)
            .setMinimumLoggingLevel(Log.DEBUG)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        val request = OneTimeWorkRequestBuilder<SeedDBWorker>()
            .setInputData(workDataOf(SeedDBWorker.KEY_FILENAME to PLANT_DATA_FILE))
            .build()
        WorkManager.getInstance(this).enqueue(request)
    }
}