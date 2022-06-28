package com.test.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class ShortenUrlDetailModel(
    @SerializedName("self") val urlComplete: String,
    @SerializedName("short") val urlShorten: String
)
