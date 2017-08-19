package ru.indefinitedream.moscowhotels.di.component

import dagger.Subcomponent
import ru.indefinitedream.moscowhotels.di.module.HotelsModule
import ru.indefinitedream.moscowhotels.di.scopes.HotelsScope
import ru.indefinitedream.moscowhotels.ui.HotelsActivity

/**
 * Created by dmitry on 19.08.17.
 */
@HotelsScope
@Subcomponent(modules = arrayOf(HotelsModule::class))
interface HotelsComponent {

    fun inject(hotelsActivity: HotelsActivity)

}