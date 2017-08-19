package ru.indefinitedream.moscowhotels.view

import android.content.Intent
import ru.indefinitedream.moscowhotels.data.Hotel

/**
 * Created by dmitry on 19.08.17.
 */
interface HotelsView {

    fun showProgress()

    fun showHotels(hotels : List<Hotel>)

    fun showError(error : String)

    fun startActivity(intent : Intent)
}