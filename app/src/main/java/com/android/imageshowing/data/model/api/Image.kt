package com.android.imageshowing.data.model.api

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("albumId")
    var albumId: Int = 0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("url")
    var url: String = "",
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String = "",
)