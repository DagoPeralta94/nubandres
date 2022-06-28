package com.test.myapplication.presentation.recentlyUrl

import com.test.myapplication.domain.model.ShortenUrlItem

internal data class RecentlyUrlUiState(
    val inProgress: Boolean = false,
    val data: List<ShortenUrlItem> = emptyList()
)