package com.test.myapplication.presentation.searchUrl

import com.test.myapplication.domain.model.ShortenUrlItem

internal data class SearchUrlUiState(
    val inProgress: Boolean = false,
    val isUrlCorrect: Boolean = false,
    val dotError: Int? = null,
    val wwwError: Int? = null,
    val addedNewUrl: Boolean = false,
    val result: ShortenUrlItem? = null
)