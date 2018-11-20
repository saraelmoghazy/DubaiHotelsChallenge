package com.hotels.tajawal.dubaihotels

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.hotels.tajawal.dubaihotels.hotels.ui.HotelFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        replaceFragment(R.id.fragment_container, ::HotelFragment)
    }

    inline fun replaceFragment(containerViewId: Int, f: () -> Fragment): Fragment? {
        return f().apply {
            if (this !is HotelFragment)
                supportFragmentManager?.beginTransaction()?.replace(containerViewId, this)?.addToBackStack(this.tag)
                        ?.commit()
            else
                supportFragmentManager?.beginTransaction()?.replace(containerViewId, this)?.commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStack()
        else
            super.onBackPressed()
    }
}
