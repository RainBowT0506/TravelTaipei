package com.rainbowt.traveltaipei.data.repository

import com.rainbowt.traveltaipei.data.api.TravelTaipeiApi
import com.rainbowt.traveltaipei.Constants

class AttractionsRepository(private val api: TravelTaipeiApi) : BaseRepository() {
    suspend fun getAttractionsApi() = safeApiCall {
        api.getAttractions(Constants.getAttractionsUrl())
    }
}