package com.network.networkclient.clientfactory

import com.network.networkclient.BuildConfig
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import retrofit2.Retrofit


class NetworkClientFactoryTest {

    private val networkClientFactoryImpl = NetworkClientFactoryImpl(BuildConfig.BASE_URL)
    private val baseUrl = "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/"

    @Test
    fun `baseUrl should be correct`() {
        val configuredBaseUrl = networkClientFactoryImpl.retrofit.baseUrl().toString()
        val expectedBaseUrl = baseUrl

        assertEquals(expectedBaseUrl, configuredBaseUrl)
    }

    @Test
    fun `should create retrofit service`() {
        val okHttpClient = mock<OkHttpClient>()
        val retrofit = mock<Retrofit>()

        networkClientFactoryImpl.okHttpClient = okHttpClient
        networkClientFactoryImpl.retrofit = retrofit

        networkClientFactoryImpl.create(TestService::class.java)

        verify(retrofit).create(TestService::class.java)
    }

    private interface TestService
}