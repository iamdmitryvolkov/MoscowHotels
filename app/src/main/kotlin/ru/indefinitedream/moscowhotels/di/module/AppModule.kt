package ru.indefinitedream.moscowhotels.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.indefinitedream.moscowhotels.data.DataProvider
import ru.indefinitedream.moscowhotels.data.DataSource
import ru.indefinitedream.moscowhotels.data.DataSourceFactory
import ru.indefinitedream.moscowhotels.data.StorableDataSource
import ru.indefinitedream.moscowhotels.di.scopes.AppScope

/**
 * Created by dmitry on 11.08.17.
 */
@Module
class AppModule(private val context: Context, private val dataSourceFactory: DataSourceFactory) {

    @Provides
    @AppScope
    fun provideContext() : Context {
        return context
    }

    @Provides
    @AppScope
    fun provideDataSource() : DataSourceFactory {
        return dataSourceFactory
    }

    @Provides
    @AppScope
    fun provideDataProvider(sourcesFactory: DataSourceFactory) : DataProvider {
        return DataProvider(sourcesFactory.createSources(), sourcesFactory.createStorableSource())
    }

}