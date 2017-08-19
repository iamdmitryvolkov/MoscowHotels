package ru.indefinitedream.moscowhotels.di.module

import dagger.Module
import dagger.Provides
import ru.indefinitedream.moscowhotels.di.scopes.DetailsScope
import ru.indefinitedream.moscowhotels.presenter.DetailsPresenter

/**
 * Created by dmitry on 19.08.17.
 */
@Module
class DetailsModule {

    @Provides
    @DetailsScope
    fun providePresenter() : DetailsPresenter {
        return DetailsPresenter()
    }

}