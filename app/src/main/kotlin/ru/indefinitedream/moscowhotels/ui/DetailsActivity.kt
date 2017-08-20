package ru.indefinitedream.moscowhotels.ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import ru.indefinitedream.moscowhotels.R
import ru.indefinitedream.moscowhotels.data.Hotel
import ru.indefinitedream.moscowhotels.injector
import ru.indefinitedream.moscowhotels.presenter.DetailsPresenter
import ru.indefinitedream.moscowhotels.view.DetailsView
import javax.inject.Inject

/**
 * Activity to show data about selected hotel
 */
class DetailsActivity : BaseDataActivity(), DetailsView {

    @Inject
    lateinit var presenter : DetailsPresenter

    var button : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector().inject(this)
        presenter.connectView(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_details

    override fun onDestroy() {
        super.onDestroy()
        presenter.disconnectView()
        injector().eject(this)
    }

    override fun showHotel(hotel: Hotel, animated: Boolean) {
        // TODO: implement
    }

    override fun setVisibleView(view: View, animated: Boolean) {
        super.setVisibleView(view, animated)
    }

}