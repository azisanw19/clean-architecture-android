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
import java.util.concurrent.TimeUnit

class OkhttpClientTest {

    @JvmField
    @Rule
    val server = MockWebServer()

    private lateinit var okHttpClient: OkHttpClient

    @Before
    fun setup() {
        okHttpClient = ApiModule.provideOkHttpClient(ApiModule.provideRequestInterceptor())
    }

    @Test
    fun okHttpClientTest() {

        val mockResponse = MockResponse()
            .setBody("Test")

        server.enqueue(mockResponse)

        val request = Request.Builder()
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

    @Test
    fun internalServerErrorTest() {
        val mockResponse = MockResponse()
            .setResponseCode(500)

        server.enqueue(mockResponse)

        val request = Request.Builder()
            .url(server.url("/test"))
            .build()

        val response = okHttpClient.newCall(request).execute()
        Assert.assertEquals(500, response.code)
    }

    @Test
    fun requestTimeOutTest() {
        val mockResponse = MockResponse()
            .setBodyDelay(70, TimeUnit.SECONDS)
            .setBody("test")

        server.enqueue(mockResponse)

        val request = Request.Builder()
            .url(server.url("/test"))
            .build()

        Assert.assertThrows(IOException::class.java) { okHttpClient.newCall(request).execute() }
    }


}