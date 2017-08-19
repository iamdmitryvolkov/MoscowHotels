package ru.indefinitedream.moscowhotels.di

import ru.indefinitedream.moscowhotels.di.component.AppComponent
import ru.indefinitedream.moscowhotels.ui.DetailsActivity
import ru.indefinitedream.moscowhotels.ui.HotelsActivity

/**
 * Class to manage modules, scopes and presenters
 */
class Injector {

    private var appComponent : AppComponent? = null

    fun inject(hotelsActivity: HotelsActivity) {
        appComponent = DaggerAppComponent.builder().build()
    }

    fun eject(hotelsActivity: HotelsActivity) {

    }

    fun inject(detailsActivity: DetailsActivity) {

    }

    fun eject(detailsActivity: DetailsActivity) {

    }
}