package ru.indefinitedream.moscowhotels.data

import io.reactivex.Single

/**
 * Created by dmitry on 19.08.17.
 */
class DBSource : StorableDataSource {

    override fun isDataAvailable(): Boolean {
        return false
    }

    override fun getData(): Single<List<Hotel>> {
        // TODO: implement
        return Single.create { it.onError(Error("store is not ready")) }
    }

    override fun storeData(data: List<Hotel>) {
        // TODO: implement
    }

}