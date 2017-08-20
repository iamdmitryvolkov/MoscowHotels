package ru.indefinitedream.moscowhotels.view

import ru.indefinitedream.moscowhotels.data.Hotel

/**
 * Created by dmitry on 19.08.17.
 */
interface HotelsView : BaseView{

    fun showHotels(hotels : List<Hotel>, animated : Boolean)
}