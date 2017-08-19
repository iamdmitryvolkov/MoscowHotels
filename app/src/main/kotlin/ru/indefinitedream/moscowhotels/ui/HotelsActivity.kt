package ru.indefinitedream.moscowhotels.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.indefinitedream.moscowhotels.data.Hotel
import ru.indefinitedream.moscowhotels.R
import ru.indefinitedream.moscowhotels.api
import ru.indefinitedream.moscowhotels.view.HotelsView

private val STARS_NAMES = arrayOf(
        "без звезд",
        "одна звезда",
        "две звезды",
        "три звезды",
        "четыре звезды",
        "пять звезд"
)

// TODO: put to strings
private val SHORT_AREA_NAMES = mapOf(
        "ЦАО" to "Центральный административный округ",
        "ЮАО" to "Южный административный округ",
        "ВАО" to "Восточный административный округ",
        "ЗАО" to "Западный административный округ",
        "СВАО" to "Северо-Восточный административный округ",
        "СЗАО" to "Северо-Западный административный округ",
        "ЮВАО" to "Юго-Восточный административный округ",
        "ЮЗАО" to "Юго-Западный административный округ"
)

/**
 * Main Activity class
 */
class HotelsActivity : AppCompatActivity(), HotelsView {

    var recycler : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = (findViewById(R.id.recycler) as RecyclerView).also {
            it.layoutManager = LinearLayoutManager(this)
            it.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        }

        load()
    }

    private fun load() {
        api?.getHotels()?.enqueue(object : Callback<List<Hotel>> {
            override fun onResponse(call: Call<List<Hotel>>, response: Response<List<Hotel>>) {
                showHotels(response.body())
            }

            override fun onFailure(call: Call<List<Hotel>>, t: Throwable) {
                t.printStackTrace()
                // TODO: show error
            }
        })
    }

    override fun showHotels(hotels : List<Hotel>) {
        recycler!!.adapter = HotelsAdapter(hotels)
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

/**
 * View holder to show
 */
class HotelsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val name : TextView = itemView.findViewById(R.id.name) as TextView
    val address : TextView = itemView.findViewById(R.id.addressText) as TextView
    val rating: RatingBar = itemView.findViewById(R.id.ratingBar) as RatingBar

    init {
        itemView.setOnClickListener {
            val context = itemView.context
            val intent = Intent(context, DetailsActivity::class.java)
            context.startActivity(intent)
        }
    }
}

/**
 * Adapter for hotels
 */
class HotelsAdapter(var hotels : List<Hotel>) : RecyclerView.Adapter<HotelsHolder>() {

    init {
        hotels = hotels.sortedWith(Comparator { o1, o2 -> getRating(o2).compareTo(getRating(o1)) })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HotelsHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.hotel_item, parent, false)
        return HotelsHolder(view)
    }

    override fun getItemCount(): Int = hotels.size

    override fun onBindViewHolder(holder: HotelsHolder?, position: Int) {
        val hotel = hotels[position]
        holder!!.name.text = hotel.cells?.name
        holder.address.text = getAddress(hotel)
        val stars = getRating(hotel)
        holder.rating.visibility = if (stars != -1) View.VISIBLE else View.INVISIBLE
        if (stars >= 0) holder.rating.rating = stars.toFloat()
    }

    fun getAddress(hotel : Hotel) : String {
        val address = hotel.cells!!.address
        if (address == null) {
            return ""
        } else {
            return address
        }
    }

    fun getRating(hotel : Hotel) : Int = STARS_NAMES.indexOf(hotel.cells!!.category)
}