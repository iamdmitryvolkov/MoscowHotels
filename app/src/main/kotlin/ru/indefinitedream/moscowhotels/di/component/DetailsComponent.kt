package ru.indefinitedream.moscowhotels.di.component

import dagger.Subcomponent
import ru.indefinitedream.moscowhotels.di.module.DetailsModule
import ru.indefinitedream.moscowhotels.di.scopes.DetailsScope
import ru.indefinitedream.moscowhotels.ui.DetailsActivity

/**
 * Created by dmitry on 19.08.17.
 */
@DetailsScope
@Subcomponent(modules = arrayOf(DetailsModule::class))
interface DetailsComponent {

    fun inject(detailsActivity: DetailsActivity)

}