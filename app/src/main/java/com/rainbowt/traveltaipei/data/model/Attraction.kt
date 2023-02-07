package com.rainbowt.traveltaipei.data.model

data class Attraction(
    var total: Int = 0,
    var data: List<Data> = emptyList()
)

data class Data(
    var name: String? = null,
    var introduction: String? = null,
    var address: String? = null,
    var modified: String? = null,
    var official_site: String? = null,
    var images: List<ImageItem> = emptyList()
)

data class ImageItem(
    var src: String? = null
)