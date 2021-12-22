package com.example.core

import com.google.gson.annotations.SerializedName

data class MobileImageEntity constructor(
    @SerializedName("mobile_id")
    val mobile_id: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("url")
    val url: String
)
