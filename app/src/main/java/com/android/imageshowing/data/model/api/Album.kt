package com.android.imageshowing.data.model.api

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("userId")
    var userId: Int = 0,
    @SerializedName("title")
    var title: String = "",
)