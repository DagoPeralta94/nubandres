package com.test.myapplication.controllers

import com.test.myapplication.domain.useCase.UrlValidatorUseCaseImpl
import com.test.myapplication.domain.useCase.success
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UrlValidatorTest {

    private val urlValidator by lazy(::UrlValidatorUseCaseImpl)

    @Test
    fun `WHEN url contain www and dot THEN SHOW success`() = runBlocking {
        val result = urlValidator(URL_SUCCESS)
        assert(result.success)
    }

    @Test
    fun `WHEN url doesn't contain dot THEN SHOW error`() = runBlocking {
        val result = urlValidator(URL_NOT_DOT_YES_WWW)
        assertFalse(result.containDot)
    }

    @Test
    fun `WHEN url doesn't contain www THEN SHOW error`() = runBlocking {
        val result = urlValidator(URL_YES_DOT_NOT_WWW)
        assertFalse(result.containWww)
    }

    @Test
    fun `WHEN url doesn't contain dot but contain www THEN SHOW error`() = runBlocking {
        val result = urlValidator(URL_NOT_DOT_YES_WWW)
        assertFalse(result.containDot)
    }

    @Test
    fun `WHEN url does contain dot but doesn't contain www THEN SHOW error`() = runBlocking {
        val result = urlValidator(URL_YES_DOT_NOT_WWW)
        assertFalse(result.containWww)
    }

    @Test
    fun `WHEN url doesn't contain dot neither www THEN SHOW error`() = runBlocking {
        val result = urlValidator(URL_NO_DOT_NOT_WWW)
        assertFalse(result.containWww)
    }

    companion object {
        private const val URL_SUCCESS = "www.nu.com.co"
        private const val URL_NO_DOT_NOT_WWW = "nu"
        private const val URL_YES_DOT_NOT_WWW = "nu.com.co"
        private const val URL_NOT_DOT_YES_WWW = "wwwnucomco"
    }
}