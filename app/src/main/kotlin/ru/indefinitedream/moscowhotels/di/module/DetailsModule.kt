package ru.indefinitedream.moscowhotels.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.indefinitedream.moscowhotels.data.DataProvider
import ru.indefinitedream.moscowhotels.di.scopes.DetailsScope
import ru.indefinitedream.moscowhotels.presenter.DetailsPresenter

/**
 * Created by dmitry on 19.08.17.
 */
@Module
class DetailsModule {

    @Provides
    @DetailsScope
    fun providePresenter(context : Context, provider : DataProvider) : DetailsPresenter {
        return DetailsPresenter(context, provider)
    }

}