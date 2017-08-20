package ru.indefinitedream.moscowhotels.presenter

import android.content.Context
import ru.indefinitedream.moscowhotels.data.DataProvider
import ru.indefinitedream.moscowhotels.data.Hotel
import ru.indefinitedream.moscowhotels.view.DetailsView


const val KEY_HOTEL_ID = "hotelID"
const val WRONG_TARGET_ID = -1

/**
 * Created by dmitry on 19.08.17.
 */
class DetailsPresenter(private val context : Context, private val provider : DataProvider)
    : BasePresenter<DetailsView>() {

    var loadedHotel : Hotel? = null
    var isLoading = false
    var targetId = WRONG_TARGET_ID

    private fun loadHotel() {
        isLoading = true
        provider.getData().subscribe({
            notifyLoadFinished(if (targetId != WRONG_TARGET_ID) it[targetId] else null)
        }, {
            notifyLoadFinished(null)
        })
    }

    private fun notifyLoadFinished(hotel : Hotel?) {
        loadedHotel = hotel
        isLoading = false
        notifyView(true)
    }

    private fun handleIntent() {
        val intent = view!!.getIntent()
        val id = intent.getIntExtra(KEY_HOTEL_ID, WRONG_TARGET_ID)
        if (id == targetId) return
        targetId = id
        if (targetId != WRONG_TARGET_ID && !isLoading) loadHotel()
    }

    private fun notifyView(animated : Boolean) {
        if (loadedHotel == null) {
            view?.showError(animated)
        } else {
            view?.showHotel(loadedHotel!!, animated)
        }
    }

    override fun connectView(view: DetailsView) {
        super.connectView(view)
        handleIntent()
        if (isLoading) {
            view.showProgressBar(false)
        } else {
            notifyView(false)
        }
    }

}