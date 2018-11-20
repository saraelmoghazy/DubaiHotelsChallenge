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
import com.hotels.tajawal.dubaihotels.MainActivity
import com.hotels.tajawal.dubaihotels.R
import com.hotels.tajawal.dubaihotels.base.BaseFragment
import com.hotels.tajawal.dubaihotels.databinding.HotelsFragmentBinding
import com.hotels.tajawal.dubaihotels.hotel_details.HotelDetailsFragment
import com.hotels.tajawal.dubaihotels.hotels.model.Hotel

/**
 * Show hotels in grid layout.
 * Created by Sara Elmoghazy.
 */
class HotelsFragment : BaseFragment<HotelsFragmentBinding, HotelViewModel>() {

    lateinit var hotelViewModel: HotelViewModel

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
        hotelViewModel = activity?.run {
            ViewModelProviders.of(this).get(HotelViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        return hotelViewModel
    }

    override fun onViewInflated(savedInstanceState: Bundle?, rootView: View?) {
        binding = viewDataBinding
        binding.rvHotels.layoutManager = GridLayoutManager(this.context, 2)
        subscribeToHotelsLiveData()

    }

    private fun subscribeToHotelsLiveData() {
        viewModel.getHotels().observe(this, Observer<List<Hotel>> { hotels ->
            var hotelAdapter = HotelAdapter(hotels
                    ?: ArrayList<Hotel>(), { hotel -> onHotelItemClicked(hotel) })
            binding.rvHotels.adapter = hotelAdapter
        })
    }

    fun onHotelItemClicked(hotel: Hotel) {
        viewModel.onHotelSelected(hotel)
        navigateToHotelDetails()

    }

    fun navigateToHotelDetails() {
        (activity as MainActivity).replaceFragment(R.id.fragment_container, ::HotelDetailsFragment)
    }
}