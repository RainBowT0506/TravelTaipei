package com.rainbowt.traveltaipei.data.api

import com.rainbowt.traveltaipei.data.model.Attraction
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface TravelTaipeiApi {
    @GET
    @Headers("accept: application/json")
    suspend fun getAttractions(@Url url: String): Attraction
}