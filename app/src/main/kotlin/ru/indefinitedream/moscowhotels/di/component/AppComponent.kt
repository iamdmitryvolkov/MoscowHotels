package ru.indefinitedream.moscowhotels.di.component

import dagger.Component
import ru.indefinitedream.moscowhotels.di.module.AppModule
import ru.indefinitedream.moscowhotels.ui.HotelsActivity
import javax.inject.Singleton

/**
 * Created by dmitry on 11.08.17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(hotelsActivity: HotelsActivity)

}