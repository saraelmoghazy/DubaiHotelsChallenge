//package com.hotels.tajawal.dubaihotels.hotels.ui
//
//import android.arch.lifecycle.ViewModelProviders
//import android.os.Bundle
//import android.support.v4.widget.NestedScrollView
//import android.view.View
//
//import com.hotels.tajawal.dubaihotels.HotelViewModel
//import com.hotels.tajawal.dubaihotels.R
//import com.hotels.tajawal.dubaihotels.base.BaseFragment
//
//
///**
// * Created by Sara Elmoghazy on 19,November,2018
// */
//class HotelFragment : BaseFragment<HotelsFragmentBinding, HotelViewModel>() {
//
//    val hotelViewModel: HotelViewModel by lazy {
//        ViewModelProviders.of(this).get(HotelViewModel::class.java)
//    }
//
//    lateinit var binding: HotelsFragmentBinding
//
//    override fun getScrollView(): NestedScrollView {
//        return binding.scrollView
//    }
//
//    override fun getBindingVariable(): Int {
//        return BR.viewModel
//    }
//
//    override fun getLayoutId(): Int {
//        return R.layout.hotels_fragment
//    }
//
//    override fun getViewModel(): HotelViewModel {
//        return hotelViewModel
//    }
//
//    override fun onViewInflated(savedInstanceState: Bundle?, rootView: View?) {
//        binding = viewDataBinding
//    }
//}