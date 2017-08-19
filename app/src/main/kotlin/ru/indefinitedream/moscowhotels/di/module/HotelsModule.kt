package ru.indefinitedream.moscowhotels.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.indefinitedream.moscowhotels.data.DataProvider
import ru.indefinitedream.moscowhotels.di.scopes.HotelsScope
import ru.indefinitedream.moscowhotels.presenter.HotelsListPresenter

/**
 * Created by dmitry on 19.08.17.
 */
@Module
class HotelsModule {

    @Provides
    @HotelsScope
    fun providePresenter(context : Context, provider : DataProvider) : HotelsListPresenter {
        return HotelsListPresenter(context, provider)
    }
}