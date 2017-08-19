package ru.indefinitedream.moscowhotels.presenter

/**
 * Created by dmitry on 19.08.17.
 */
abstract class BasePresenter<T> : Presenter<T> {

    protected var view : T? = null

    override fun connectView(view: T) {
        this.view = view
    }

    override fun disconnectView() {
        view = null
    }

}