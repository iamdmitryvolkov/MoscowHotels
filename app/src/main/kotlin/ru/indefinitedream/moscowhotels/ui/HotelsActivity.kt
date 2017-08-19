package ru.indefinitedream.moscowhotels.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import ru.indefinitedream.moscowhotels.data.Hotel
import ru.indefinitedream.moscowhotels.R
import ru.indefinitedream.moscowhotels.injector
import ru.indefinitedream.moscowhotels.presenter.HotelsListPresenter
import ru.indefinitedream.moscowhotels.view.HotelsView
import javax.inject.Inject


const val ANIMATION_DURATION = 500L
const val VISIBLE_ALPHA = 1f
const val INVISIBLE_ALPHA = 0f

/**
 * Main Activity class
 */
class HotelsActivity : AppCompatActivity(), HotelsView, OnItemClickListener {

    @Inject
    lateinit var presenter : HotelsListPresenter

    @BindView(R.id.recycler)
    lateinit var recycler : RecyclerView

    @BindView(R.id.progress)
    lateinit var progress : ProgressBar

    @BindView(R.id.error_message)
    lateinit var error : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injector().inject(this)

        ButterKnife.bind(this)
        recycler.also {
            it.layoutManager = LinearLayoutManager(this)
            it.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        }

        presenter.connectView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.disconnectView()
        injector().eject(this)
    }


    override fun showHotels(hotels : List<Hotel>, animated : Boolean) {
        setVisibleView(recycler, animated)
        recycler.adapter = HotelsAdapter(hotels, this)
    }

    override fun showProgress(animated : Boolean) {
        setVisibleView(progress, animated)
    }

    override fun showError(animated : Boolean) {
        setVisibleView(error, animated)
    }

    override fun onItemClick(adapterPosition: Int) {
        presenter.onItemClick(adapterPosition)
    }

    private fun setVisibleView(view : View, animated : Boolean) {
        setVisibility(recycler, view == recycler, animated)
        setVisibility(progress, view == progress, animated)
        setVisibility(error, view == error, animated)
    }

    private fun setVisibility(view : View, visible: Boolean, animated : Boolean) {
        val alpha = if (visible) VISIBLE_ALPHA else INVISIBLE_ALPHA
        if (animated) {
            view.animate().alpha(alpha).setDuration(ANIMATION_DURATION).start()
        } else {
            view.alpha = alpha
        }
    }
}

/**
 * View holder to show
 */
class HotelsHolder(itemView: View, private val itemClickListener: OnItemClickListener?)
    : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.name)
    lateinit var name : TextView
    @BindView(R.id.addressText)
    lateinit var address : TextView
    @BindView(R.id.ratingBar)
    lateinit var rating: RatingBar

    init {
        ButterKnife.bind(this, itemView)
        itemView.setOnClickListener {
            itemClickListener?.onItemClick(adapterPosition)
        }
    }
}

/**
 * Adapter for hotels
 */
class HotelsAdapter(var hotels : List<Hotel>, private val onItemClickListener: OnItemClickListener?)
    : RecyclerView.Adapter<HotelsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HotelsHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.hotel_item, parent, false)
        return HotelsHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int = hotels.size

    override fun onBindViewHolder(holder: HotelsHolder?, position: Int) {
        val hotel = hotels[position]
        holder!!.name.text = hotel.cells?.name
        holder.address.text = getAddress(hotel)
        val stars = hotel.cells!!.stars
        holder.rating.visibility = if (stars != -1) View.VISIBLE else View.INVISIBLE
        if (stars != null) holder.rating.rating = stars.toFloat()
    }

    fun getAddress(hotel : Hotel) : String {
        val address = hotel.cells!!.address
        if (address == null) {
            return ""
        } else {
            return address
        }
    }
}

interface OnItemClickListener {

    fun onItemClick(adapterPosition : Int)
}