package ru.indefinitedream.moscowhotels.data

import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit api interface
 */
interface MosApi {

    @GET("datasets/2343/rows")
    fun getHotels(): Call<List<Hotel>>
}
