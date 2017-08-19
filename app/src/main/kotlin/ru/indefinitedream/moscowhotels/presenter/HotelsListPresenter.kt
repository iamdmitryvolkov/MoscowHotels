package ru.indefinitedream.moscowhotels.presenter

import android.content.Context
import android.content.Intent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.indefinitedream.moscowhotels.data.DataProvider
import ru.indefinitedream.moscowhotels.data.Hotel
import ru.indefinitedream.moscowhotels.ui.DetailsActivity
import ru.indefinitedream.moscowhotels.ui.KEY_HOTEL_ID
import ru.indefinitedream.moscowhotels.view.HotelsView

/**
 * Created by dmitry on 19.08.17.
 */
class HotelsListPresenter(private val context : Context, private val provider : DataProvider)
    : BasePresenter<HotelsView>() {

    private var data : List<Hotel>? = null
    private var loading = true

    init {
        requestDataUpdate()
    }

    private fun watchNetworkState() {
        // TODO: implement broadcast receiver to watch network available
    }

    private fun requestDataUpdate() {
        provider.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data = it
                    loading = false
                    notifyView(true)
                }, {
                    it.printStackTrace()
                    loading = false
                    notifyView(true)
                    watchNetworkState()
                })
    }

    private fun notifyView(animated : Boolean) {
        view?.let {
            if (data != null) {
                it.showHotels(data!!, animated)
            } else {
                it.showError(animated)
            }
        }
    }

    override fun connectView(view: HotelsView) {
        super.connectView(view)
        if (!loading) {
            notifyView(false)
        } else {
            view.showProgress(false)
        }
    }

    fun onItemClick(position : Int) {
        val intent = Intent(context, DetailsActivity::class.java)
        // TODO: set global identifier
        intent.putExtra(KEY_HOTEL_ID, position)
        view?.startActivity(intent)
    }

}