package ru.indefinitedream.moscowhotels.presenter

/**
 * Created by dmitry on 19.08.17.
 */
interface Presenter<in T> {

    fun connectView(view : T)

    fun disconnectView()

}