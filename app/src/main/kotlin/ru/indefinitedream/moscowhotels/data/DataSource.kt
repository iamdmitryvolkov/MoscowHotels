package ru.indefinitedream.moscowhotels.data

import io.reactivex.Single

/**
 * Created by dmitry on 19.08.17.
 */
interface DataSource {

    fun isDataAvailable() : Boolean

    fun getData() : Single<List<Hotel>>

}

interface StorableDataSource : DataSource {

    fun storeData(data : List<Hotel>)
}