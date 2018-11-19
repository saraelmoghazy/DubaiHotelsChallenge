package com.hotels.tajawal.dubaihotels.hotels.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.hotels.tajawal.dubaihotels.BR
import com.hotels.tajawal.dubaihotels.HotelViewModel
import com.hotels.tajawal.dubaihotels.R
import com.hotels.tajawal.dubaihotels.base.BaseFragment
import com.hotels.tajawal.dubaihotels.databinding.HotelsFragmentBinding
import com.hotels.tajawal.dubaihotels.hotels.model.Hotel

/**
 * Created by Sara Elmoghazy on 19,November,2018
 */
class HotelFragment : BaseFragment<HotelsFragmentBinding, HotelViewModel>() {

    val hotelViewModel: HotelViewModel by lazy {
        ViewModelProviders.of(this).get(HotelViewModel::class.java)
    }

    lateinit var binding: HotelsFragmentBinding

    override fun getScrollView(): NestedScrollView {
        return binding.scrollView
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.hotels_fragment
    }

    override fun getViewModel(): HotelViewModel {
        return hotelViewModel
    }

    override fun onViewInflated(savedInstanceState: Bundle?, rootView: View?) {
        binding = viewDataBinding
        binding.rvHotels.layoutManager = GridLayoutManager(this.context, 2)
        subscribeToHotelLiveData()

    }

    private fun subscribeToHotelLiveData() {
        viewModel.getHotels().observe(this, Observer<List<Hotel>> { hotels ->
            var hotelAdapter = HotelAdapter(hotels
                    ?: ArrayList<Hotel>(), { hotel -> hotelItemClicked(hotel) })
            binding.rvHotels.adapter = hotelAdapter
        })
    }

    fun hotelItemClicked(hotel: Hotel) {
        Toast.makeText(this.getmContext(), "Clicked: ${hotel.summary.hotelName}", Toast.LENGTH_LONG).show()
    }
}