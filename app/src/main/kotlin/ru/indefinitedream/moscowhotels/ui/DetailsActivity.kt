package ru.indefinitedream.moscowhotels.ui

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import ru.indefinitedream.moscowhotels.R

/**
 * Activity to show data about selected hotel
 */
class DetailsActivity : AppCompatActivity() {

    var button : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        button = findViewById(R.id.etext) as EditText

        findViewById(R.id.button).setOnClickListener {
            it.setBackgroundColor(Color.RED)
        }

    }

}