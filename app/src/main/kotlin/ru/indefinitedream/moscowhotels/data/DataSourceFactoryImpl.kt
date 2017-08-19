package ru.indefinitedream.moscowhotels.data

import android.content.Context

/**
 * Created by dmitry on 19.08.17.
 */
class DataSourceFactoryImpl(private val context : Context) : DataSourceFactory {
    override fun createSources(): List<DataSource> {
        return listOf(ApiSource(context))
    }

    override fun createStorableSource(): List<StorableDataSource> {
        return listOf(DBSource())
    }

}