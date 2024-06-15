package org.d3if0088.miniproject1.model

import com.squareup.moshi.Json

data class Comic(
    val id: String,
    @Json (name = "title") val title: String,
    @Json (name = "genre") val genre: String,
    @Json (name = "image_id") val imageId: String,
)
