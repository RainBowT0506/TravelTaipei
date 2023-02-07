package com.rainbowt.traveltaipei

object Constants {

    fun getAttractionsUrl(): String {
        return "$BASE_URL$languageCode/Attractions/All"
    }

    const val BASE_URL = "https://www.travel.taipei/open-api/"
    const val GOOGLE_URL = "https://www.google.com.tw"
    var languageCode = "en"
}