package com.example.narcissusflower.di

import androidx.work.WorkerFactory
import com.example.narcissusflower.workers.SeedDBWorkerFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {

    @Binds
    abstract fun bindFeedWorkerFactory(factory: SeedDBWorkerFactory): WorkerFactory
}