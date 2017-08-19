package ru.indefinitedream.moscowhotels.view

import android.content.Intent
import ru.indefinitedream.moscowhotels.data.Hotel

/**
 * Created by dmitry on 19.08.17.
 */
interface HotelsView {

    fun showProgress(animated : Boolean)

    fun showHotels(hotels : List<Hotel>, animated : Boolean)

    fun showError(animated : Boolean)

    fun startActivity(intent : Intent)
}