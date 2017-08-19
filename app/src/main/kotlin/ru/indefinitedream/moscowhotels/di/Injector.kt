package ru.indefinitedream.moscowhotels.di

import ru.indefinitedream.moscowhotels.di.component.HotelsComponent
import ru.indefinitedream.moscowhotels.di.component.AppComponent
import ru.indefinitedream.moscowhotels.di.component.DetailsComponent
import ru.indefinitedream.moscowhotels.ui.DetailsActivity
import ru.indefinitedream.moscowhotels.ui.HotelsActivity

/**
 * Class to manage modules, scopes and presenters
 */
class Injector(private val appComponent: AppComponent) {

    var hotelsComponent : HotelsComponent? = null
    private set
    var detailsComponent : DetailsComponent? = null
    private set

    fun inject(hotelsActivity: HotelsActivity) {
        if (hotelsComponent == null) hotelsComponent = appComponent.plusHotelsComponent()
        hotelsComponent!!.inject(hotelsActivity)
    }

    fun eject(hotelsActivity: HotelsActivity) {
        if (hotelsActivity.isFinishing) hotelsComponent = null
    }

    fun inject(detailsActivity: DetailsActivity) {
        if (detailsComponent == null) detailsComponent = appComponent.plusDetailsComponent()
        detailsComponent!!.inject(detailsActivity)
    }

    fun eject(detailsActivity: DetailsActivity) {
        if (detailsActivity.isFinishing) detailsComponent = null
    }
}