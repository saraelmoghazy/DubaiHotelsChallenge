package com.hotels.tajawal.dubaihotels

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import com.hotels.tajawal.dubaihotels.hotels.ui.HotelFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(R.id.fragment_container, ::HotelFragment)
    }


    inline fun FragmentActivity.replaceFragment(containerViewId: Int, f: () -> Fragment): Fragment? {
        return f().apply { supportFragmentManager?.beginTransaction()?.replace(containerViewId, this)?.commit() }
    }
}
