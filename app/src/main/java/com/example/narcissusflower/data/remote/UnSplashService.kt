package com.example.narcissusflower.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface UnSplashService {

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UnSplashSearchResponse
}