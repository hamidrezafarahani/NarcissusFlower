package com.example.narcissusflower.di

import android.app.Application
import com.example.narcissusflower.BuildConfig
import com.example.narcissusflower.data.remote.UnSplashService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val BASE_URL = "https://api.unsplash.com/"


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor {
            Timber.tag("log-okhttp").d(it)
        }.apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @AuthInterceptor
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor {
            val url = it.request().url.newBuilder().apply {
                addQueryParameter("client_id", BuildConfig.UNSPLASH_ACCESS_KEY)
            }.build()
            val request = it.request().newBuilder()
                .url(url)
                /*.addHeader("Authorization", "Client-ID ${BuildConfig.UNSPLASH_ACCESS_KEY}")*/
                .build()
            it.proceed(request)
        }
    }

    @Provides
    fun provideOkHttpCache(app: Application): Cache {
        return Cache(
            directory = File(app.cacheDir, "http_cache"),
            maxSize = 50L * 1024 * 1024 // 50 MiB
        )
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        @AuthInterceptor authInterceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideRetrofit(
        client: dagger.Lazy<OkHttpClient>,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .callFactory {
                client.get().newCall(it)
            }
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideUnSplashService(retrofit: Retrofit): UnSplashService {
        return retrofit.create(UnSplashService::class.java)
    }
}