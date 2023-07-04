package com.example.narcissusflower.data.remote

import com.google.gson.annotations.SerializedName as SN

data class UnSplashSearchResponse(
    @SN("results") val results: List<UnSplashPhoto>,
    @SN("total_page") val totalPage: Int
)