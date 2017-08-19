package ru.indefinitedream.moscowhotels.data

/**
 * Created by dmitry on 19.08.17.
 */
interface DataSource {

    fun isDataAvailable() : Boolean

    fun getData() : List<Hotel>

}