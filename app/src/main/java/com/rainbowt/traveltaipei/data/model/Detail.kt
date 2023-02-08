package com.rainbowt.traveltaipei.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Detail(
    var imageUrl: String? = null,
    var name: String? = null,
    var introduction: String? = null,
    var address: String? = null,
    var lastUpdatedTime: String? = null,
    var officialSite: String? = null
) : Parcelable