package com.test.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class ShortenUrlModel(
    @SerializedName("alias") val alias: String = "",
    @SerializedName("_links") val info: ShortenUrlDetailModel? = null
)