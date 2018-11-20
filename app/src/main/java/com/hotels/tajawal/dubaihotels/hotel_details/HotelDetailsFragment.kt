package com.hotels.tajawal.dubaihotels.hotel_details

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hotels.tajawal.dubaihotels.BR
import com.hotels.tajawal.dubaihotels.HotelViewModel
import com.hotels.tajawal.dubaihotels.R
import com.hotels.tajawal.dubaihotels.base.BaseFragment
import com.hotels.tajawal.dubaihotels.databinding.HotelDetailsFragmentBinding

/**
 * Hotel Details fragment , show hotel details / hotel image in full screen
 * Created by Sara Elmoghazy.
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
        subscribeTShowFullImageLiveData()
    }

    private fun subscribeTShowFullImageLiveData() {
        viewModel.getShowFullImageLiveData().observe(this, Observer<String> { url ->
            showImageFullScreen(url)
        })
    }

    fun showImageFullScreen(url: String?) {
        if (url != null) {
            var imageDialog = Dialog(this.getmContext(), R.style.DialogFullscreen)
            imageDialog.setContentView(R.layout.hotel_full_screen)
            var icHotel: ImageView = imageDialog.findViewById(R.id.icHotel)
            Glide.with(icHotel.context).load(url).into(icHotel)
            imageDialog.setCancelable(false)
            imageDialog.show()
            icHotel.setOnClickListener { v ->
                imageDialog.hide()
                viewModel.getShowFullImageLiveData().postValue(null)
            }
        }

    }
}