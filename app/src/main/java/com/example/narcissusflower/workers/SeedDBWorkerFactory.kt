package com.example.narcissusflower.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.narcissusflower.data.local.dao.PlantDao
import javax.inject.Inject

class SeedDBWorkerFactory @Inject constructor(private val plantDao: PlantDao) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return SeedDBWorker(appContext, workerParameters, plantDao)
    }
}