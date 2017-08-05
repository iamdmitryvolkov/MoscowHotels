package ru.indefinitedream.moscowhotels

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

/**
 * Main Activity class
 */
class MainActivity : AppCompatActivity() {

    var recycler : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = (findViewById(R.id.recycler) as RecyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity,
                    DividerItemDecoration.VERTICAL))
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

    private fun showHotels(hotels : List<Hotel>) {
        recycler?.adapter = HotelsAdapter(hotels)
    }

}

/**
 * View holder to show
 */
class HotelsHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    val name : TextView = itemView!!.findViewById(R.id.name) as TextView
    val address : TextView = itemView!!.findViewById(R.id.addressText) as TextView
    val rating: RatingBar = itemView!!.findViewById(R.id.ratingBar) as RatingBar

}

/**
 * Adapter for hotels
 */
class HotelsAdapter(var hotels : List<Hotel>) : RecyclerView.Adapter<HotelsHolder>() {

    init {
        hotels = hotels.sortedWith(Comparator { o1, o2 -> getRating(o1).compareTo(getRating(o2)) })
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
        val area = getAresShortName(hotel.cells!!.admArea)
        val address = hotel.cells!!.address
        if (area == null) {
            if (address == null) {
                return ""
            } else {
                return address
            }
        } else {
            if (address == null) {
                return area
            } else {
                return "$area, $address"
            }
        }
    }

    fun getAresShortName(name : String?) : String? {
        if (SHORT_AREA_NAMES.containsKey(name)) {
            return SHORT_AREA_NAMES[name]
        } else {
            return null
        }
    }

    fun getRating(hotel : Hotel) : Int = STARS_NAMES.indexOf(hotel.cells!!.category)
}

private val SHORT_AREA_NAMES = mapOf(
        "Центральный административный округ" to "ЦАО",
        "Южный административный округ" to "ЮАО",
        "Восточный административный округ" to "ВАО",
        "Западный административный округ" to "ЗАО",
        "Северо-Восточный административный округ" to "СВАО",
        "Северо-Западный административный округ" to "СЗАО",
        "Юго-Восточный административный округ" to "ЮВАО",
        "Юго-Западный административный округ" to "ЮЗАО"
        )

private val STARS_NAMES = arrayOf(
        "без звезд",
        "одна звезда",
        "две звезды",
        "три звезды",
        "четыре звезды",
        "пять звезд"
)