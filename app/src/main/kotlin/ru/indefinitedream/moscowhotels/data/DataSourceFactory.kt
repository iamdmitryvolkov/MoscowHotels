package ru.indefinitedream.moscowhotels.data

/**
 * Created by dmitry on 19.08.17.
 */
interface DataSourceFactory {

    fun createSources() : List<DataSource>

    fun createStorableSource() : List<StorableDataSource>
}