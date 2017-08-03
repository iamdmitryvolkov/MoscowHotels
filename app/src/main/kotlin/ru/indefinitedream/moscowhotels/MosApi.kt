package ru.indefinitedream.moscowhotels

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit api interface
 */
interface MosApi {

    @GET("/users/{name}/repos")
    fun getAll(@Path("name") user: String): Call<List<HotelModel>>
}
