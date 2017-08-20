package ru.indefinitedream.moscowhotels.view

import android.content.Intent
import ru.indefinitedream.moscowhotels.data.Hotel

/**
 * Created by dmitry on 19.08.17.
 */
interface DetailsView : BaseView {

    fun showHotel(hotel : Hotel, animated : Boolean)

    fun getIntent() : Intent

}