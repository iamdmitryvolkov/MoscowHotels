package ru.indefinitedream.moscowhotels.di.component

import dagger.Component
import ru.indefinitedream.moscowhotels.di.module.AppModule
import ru.indefinitedream.moscowhotels.di.scopes.AppScope

/**
 * Created by dmitry on 11.08.17.
 */
@AppScope
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun plusDetailsComponent() : DetailsComponent

    fun plusHotelsComponent() : HotelsComponent

}