package ru.indefinitedream.moscowhotels.data

import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable

/**
 * Created by dmitry on 19.08.17.
 */
class DataProvider(private val sources: List<DataSource>,
                   private val storableSources : List<StorableDataSource>) {

    private var data : List<Hotel>? = null

    private fun generateException() : Exception {
        return Exception("No available data source")
    }

    fun getData() : Single<List<Hotel>> {
        if (data != null) return Single.just(data)


        // TODO: check it
        /*getDataFromSource(sources)
                .doOnSuccess { updateStorableSources(it) }
                .toSingle(getDataFromSource(storableSources).toSingle().blockingGet())*/


        return Single.create {
            res ->
            getDataFromSource(sources).subscribe({
                res.onSuccess(it)
                updateStorableSources(it)
            }, {
                res.onError(it)
            }, {
                getDataFromSource(storableSources).subscribe({
                    res.onSuccess(it)
                }, {
                    res.onError(it)
                }, {
                    res.onError(generateException())
                })
            })
        }

    }

    private fun getDataFromSource(sources : List<DataSource>) : Maybe<List<Hotel>> {
        return sources.toObservable()
                .filter { it.isDataAvailable() }
                .firstElement()
                .map { it.getData().blockingGet() }
    }

    private fun updateStorableSources(data : List<Hotel>) {
        storableSources.forEach { it.storeData(data) }
    }

}