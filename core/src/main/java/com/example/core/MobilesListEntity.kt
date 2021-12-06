package com.example.core

import com.google.gson.annotations.SerializedName

data class MobilesListEntity constructor(
    @SerializedName("thumbImageURL")
    val thumbImageURL: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("price")
    val price: Double
)
