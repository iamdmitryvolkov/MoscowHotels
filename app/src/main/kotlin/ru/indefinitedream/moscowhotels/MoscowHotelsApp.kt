package ru.indefinitedream.moscowhotels;

import android.app.Application;

import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val CACHE_SIZE : Long = 10 * 1024 * 1024
private const val API_URL = "https://apidata.mos.ru/v1/datasets/2343/rows"

/**
 * Application object.
 */
class MoscowHotelsApp : Application() {

    private var api : MosApi? = null

    override fun onCreate() {
        super.onCreate()

        val client = OkHttpClient.Builder()
                .cache(Cache(cacheDir, CACHE_SIZE))
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api = retrofit.create(MosApi::class.java)
    }
}