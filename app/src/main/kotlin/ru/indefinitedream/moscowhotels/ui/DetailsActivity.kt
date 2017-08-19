package ru.indefinitedream.moscowhotels.ui

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import ru.indefinitedream.moscowhotels.R
import ru.indefinitedream.moscowhotels.injector
import ru.indefinitedream.moscowhotels.presenter.DetailsPresenter
import ru.indefinitedream.moscowhotels.view.DetailsView
import javax.inject.Inject

const val KEY_HOTEL_ID = "hotelID"

/**
 * Activity to show data about selected hotel
 */
class DetailsActivity : AppCompatActivity(), DetailsView {

    @Inject
    lateinit var presenter : DetailsPresenter

    var button : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        injector().inject(this)
        presenter.connectView(this)

        button = findViewById(R.id.etext) as EditText

        findViewById(R.id.button).setOnClickListener {
            it.setBackgroundColor(Color.RED)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.disconnectView()
        injector().eject(this)
    }

}