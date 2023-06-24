package com.canwar.baseproject

import com.canwar.baseproject.di.ApiModule
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.IOException
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InterceptorTest {

    @JvmField
    @Rule
    public val server = MockWebServer()

    private lateinit var okHttpClient: OkHttpClient

    @Before
    fun setup() {
        okHttpClient = ApiModule.provideOkHttpClient()
    }

    @Test
    fun loggingInterceptorTest() {

        val mockResponse = MockResponse()
            .setBody("Test")
            .setHeader("API", "")

        server.enqueue(mockResponse)

        val request = Request
            .Builder()
            .header("API", "API")
            .url(server.url("/test"))
            .build()

        try {
            val response = okHttpClient.newCall(request).execute()
            Assert.assertEquals("Test", response.body?.string())
            Assert.assertEquals(200, response.code)
        } catch (e: IOException) {
            e.printStackTrace()
            throw e
        }
    }


}