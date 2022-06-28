package com.test.myapplication.useCases

import com.test.myapplication.data.SearchUrlRepository
import com.test.myapplication.domain.model.ShortenUrlDetailItem
import com.test.myapplication.domain.model.ShortenUrlItem
import io.mockk.impl.annotations.RelaxedMockK

open class UseCaseHelper {

    @RelaxedMockK
    internal lateinit var urlRepository: SearchUrlRepository

    internal fun getShortenDummiesUrl(): ShortenUrlItem =
        ShortenUrlItem(
            alias = URL_ALIAS,
            ShortenUrlDetailItem(
                urlComplete = URL_COMPLETE,
                urlShorten = URL_SHORTEN
            )
        )

    companion object {
        internal const val URL_COMPLETE = "www.nu.com.co"
        private const val URL_SHORTEN = "www.data.com/23123"
        private const val URL_ALIAS = "23123"
    }
}