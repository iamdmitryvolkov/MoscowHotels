package ru.indefinitedream.moscowhotels

import android.app.Application

import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.indefinitedream.moscowhotels.data.MosApi

private const val CACHE_SIZE : Long = 10 * 1024 * 1024
private const val API_URL = "https://apidata.mos.ru/v1/"
private const val API_KEY = "api_key"
private const val KEY = "0e0a8e267c408d925e1eeaf73a2a6e08" // TODO: get from resources

var api : MosApi? = null

/**
 * Application object.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val client = OkHttpClient.Builder()
                .cache(Cache(cacheDir, CACHE_SIZE))
                .addInterceptor {
                    val url = it.request().url()
                            .newBuilder()
                            .addQueryParameter(API_KEY, KEY)
                            .build()
                    val request = it.request()
                            .newBuilder()
                            .url(url)
                            .build()
                    it.proceed(request)
                }
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api = retrofit.create(MosApi::class.java)
    }
}