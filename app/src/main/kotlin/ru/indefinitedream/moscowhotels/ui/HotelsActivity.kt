package ru.indefinitedream.moscowhotels.ui

import android.animation.Animator
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

/**
 * Main Activity class
 */
class HotelsActivity : BaseDataActivity(), HotelsView, OnItemClickListener {

    @Inject
    lateinit var presenter : HotelsListPresenter

    @BindView(R.id.recycler)
    lateinit var recycler : RecyclerView

    private var adapter : HotelsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector().inject(this)

        recycler.also {
            it.layoutManager = LinearLayoutManager(this)
            it.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        }

        presenter.connectView(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_hotels

    override fun onDestroy() {
        super.onDestroy()
        presenter.disconnectView()
        injector().eject(this)
    }

    override fun setVisibleView(view: View, animated: Boolean) {
        setVisibility(recycler, view == recycler, animated)
        super.setVisibleView(view, animated)
    }

    override fun showHotels(hotels : List<Hotel>, animated : Boolean) {
        setVisibleView(recycler, animated)
        if (adapter == null) {
            adapter = HotelsAdapter(hotels, this)
            recycler.adapter = adapter
        } else {
            adapter!!.hotels = hotels
            adapter!!.notifyDataSetChanged()
        }
    }

    override fun onItemClick(adapterPosition: Int) {
        presenter.onItemClick(adapterPosition)
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

    private fun getAddress(hotel : Hotel) : String {
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