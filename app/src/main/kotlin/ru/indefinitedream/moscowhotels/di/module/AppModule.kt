package ru.indefinitedream.moscowhotels.di.module

import dagger.Module
import dagger.Provides
import ru.indefinitedream.moscowhotels.data.DataProvider
import javax.inject.Singleton

/**
 * Created by dmitry on 11.08.17.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDataProvider() : DataProvider {
        return DataProvider()
    }

}