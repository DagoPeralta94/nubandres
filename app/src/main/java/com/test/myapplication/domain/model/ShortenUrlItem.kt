package com.test.myapplication.domain.model

import com.test.myapplication.data.database.entities.ShortenUrlEntity
import com.test.myapplication.data.model.ShortenUrlModel

class ShortenUrlItem(
    val alias: String,
    val info: ShortenUrlDetailItem
)

fun ShortenUrlModel.toDomain() = ShortenUrlItem(
    alias = alias,
    info = ShortenUrlDetailItem(
        info?.urlComplete,
        info?.urlShorten
    )
)

fun ShortenUrlEntity.toDomain() = ShortenUrlItem(
    alias = alias,
    info = ShortenUrlDetailItem(
        urlComplete,
        urlShorten
    )
)
