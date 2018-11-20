package com.hotels.tajawal.dubaihotels.hotel_details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.view.View
import com.hotels.tajawal.dubaihotels.BR
import com.hotels.tajawal.dubaihotels.HotelViewModel
import com.hotels.tajawal.dubaihotels.R
import com.hotels.tajawal.dubaihotels.base.BaseFragment
import com.hotels.tajawal.dubaihotels.databinding.HotelDetailsFragmentBinding

/**
 * Created by Sara Elmoghazy on 20,November,2018
 */

class HotelDetailsFragment : BaseFragment<HotelDetailsFragmentBinding, HotelViewModel>() {

    lateinit var hotelViewModel: HotelViewModel

    lateinit var binding: HotelDetailsFragmentBinding

    override fun getScrollView(): NestedScrollView {
        return binding.scrollView
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.hotel_details_fragment
    }

    override fun getViewModel(): HotelViewModel {
        hotelViewModel = activity?.run {
            ViewModelProviders.of(this).get(HotelViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        return hotelViewModel
    }

    override fun onViewInflated(savedInstanceState: Bundle?, rootView: View?) {
        binding = viewDataBinding
    }
}