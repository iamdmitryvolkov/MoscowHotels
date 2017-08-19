package ru.indefinitedream.moscowhotels

import android.app.Application

import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.indefinitedream.moscowhotels.data.DataSourceFactoryImpl
import ru.indefinitedream.moscowhotels.data.MosApi
import ru.indefinitedream.moscowhotels.di.Injector
import ru.indefinitedream.moscowhotels.di.component.AppComponent
import ru.indefinitedream.moscowhotels.di.component.DaggerAppComponent
import ru.indefinitedream.moscowhotels.di.module.AppModule

var injectorIntance : Injector? = null

fun injector() : Injector {
    return injectorIntance!!
}

/**
 * Application object.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setDefaultInjector()
    }

    private fun setDefaultInjector() {
        val appModule = AppModule(applicationContext, DataSourceFactoryImpl(applicationContext))
        val component = DaggerAppComponent.builder()
                .appModule(appModule)
                .build()
        injectorIntance = Injector(component)
    }
}