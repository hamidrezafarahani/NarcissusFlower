package com.example.narcissusflower.data.remote

import com.google.gson.annotations.SerializedName as SN

data class UnSplashPhoto(
    @SN("id") val id: String,
    @SN("urls") val urls: UnSplashPhotoUrls,
    @SN("user") val user: UnSplashUser
)

data class UnSplashPhotoUrls(
    @SN("small") val small: String
)

data class UnSplashUser(
    @SN("name") val name: String,
    @SN("username") val username: String
) {
    val attributionUrl: String
        get() {
            return "https://unsplash.com/$username?utm_source=sunflower&utm_medium=referral"
        }
}