package ru.indefinitedream.moscowhotels.data

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Single
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.indefinitedream.moscowhotels.R

private const val CACHE_SIZE : Long = 10 * 1024 * 1024
private const val API_URL = "https://apidata.mos.ru/v1/"
private const val API_KEY = "api_key"

private val STARS_NAMES = arrayOf(
        "без звезд",
        "одна звезда",
        "две звезды",
        "три звезды",
        "четыре звезды",
        "пять звезд"
)

class ApiSource(private val context : Context) : DataSource {

    private var api : MosApi? = null

    private fun initApi() {
        val client = OkHttpClient.Builder()
                .cache(Cache(context.cacheDir, CACHE_SIZE))
                .addInterceptor {
                    val url = it.request().url()
                            .newBuilder()
                            .addQueryParameter(API_KEY, context.getString(R.string.api_key))
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        api = retrofit.create(MosApi::class.java)
    }

    private fun apiSingle() : Single<MosApi> {
        return Single.create {
            if (api == null) initApi()
            it.onSuccess(api!!)
        }
    }

    override fun isDataAvailable(): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return manager.activeNetworkInfo?.isConnected ?: false
    }

    private fun handleRating(hotels : List<Hotel>) : List<Hotel> {
        return hotels.map { it.apply { it.cells!!.stars = getRating(it) } }
    }

    private fun getRating(hotel : Hotel) : Int = STARS_NAMES.indexOf(hotel.cells!!.category)

    override fun getData(): Single<List<Hotel>> {
        return apiSingle()
                .flatMap { it.hotels() }
                .map { handleRating(it) }
    }

}