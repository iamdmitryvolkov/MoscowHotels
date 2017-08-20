package ru.indefinitedream.moscowhotels.view

import android.content.Intent

/**
 * Created by dmitry on 20.08.17.
 */
interface BaseView {

    fun showProgressBar(animated: Boolean)

    fun showError(animated : Boolean)

    fun startActivity(intent : Intent)
}