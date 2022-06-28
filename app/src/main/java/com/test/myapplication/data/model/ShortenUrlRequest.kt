package com.test.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class ShortenUrlRequest(
    @SerializedName("url") val url: String
)