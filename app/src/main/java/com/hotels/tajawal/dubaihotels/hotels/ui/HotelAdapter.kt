package com.hotels.tajawal.dubaihotels.hotels.ui

import android.databinding.ViewDataBinding
import android.support.v7.widget.CardView
import com.hotels.tajawal.dubaihotels.R
import com.hotels.tajawal.dubaihotels.base.BaseAdapter
import com.hotels.tajawal.dubaihotels.base.BaseViewHolder
import com.hotels.tajawal.dubaihotels.hotels.model.Hotel

/**
 * Created by Sara Elmoghazy on 19,November,2018
 */
class HotelAdapter(val hotels: List<Hotel>, private val clickListener: (Hotel) -> Unit) : BaseAdapter<Hotel, ViewDataBinding>() {


    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.binding.root.findViewById<CardView>(R.id.hotelLayout)
                .setOnClickListener { clickListener(getItemForPosition(position)) }
    }

    override fun getItemForPosition(position: Int): Hotel {
        return hotels[position]
    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    override fun getLayout(): Int {
        return R.layout.partial_hotel_item
    }
}