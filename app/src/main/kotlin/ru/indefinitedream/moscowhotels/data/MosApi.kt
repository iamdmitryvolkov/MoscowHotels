package ru.indefinitedream.moscowhotels.data

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Retrofit api interface
 */
interface MosApi {

    @GET("datasets/2343/rows")
    fun hotels(): Single<List<Hotel>>
}
