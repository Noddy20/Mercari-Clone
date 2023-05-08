package com.network.networkclient.clientfactory

import androidx.annotation.VisibleForTesting
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.netowrk.networkclient.clientfactory.NetworkClientFactory
import com.network.networkclient.BuildConfig
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class NetworkClientFactoryImpl @Inject constructor(
    baseUrl: String
) : NetworkClientFactory {

    @VisibleForTesting
    lateinit var okHttpClient: OkHttpClient

    @VisibleForTesting
    fun initOkHttpClient() {
        okHttpClient = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
        }.build()
    }

    @VisibleForTesting
    lateinit var retrofit: Retrofit

    @OptIn(ExperimentalSerializationApi::class)
    @VisibleForTesting
    fun initRetrofit(baseUrl: String) {
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    init {
        initOkHttpClient()
        initRetrofit(baseUrl = baseUrl)
    }

    override fun <T> create(clientService: Class<T>): T {
        return retrofit.create(clientService)
    }
}
