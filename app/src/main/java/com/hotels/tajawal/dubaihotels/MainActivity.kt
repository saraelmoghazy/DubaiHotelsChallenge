package com.hotels.tajawal.dubaihotels

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.hotels.tajawal.dubaihotels.hotels.ui.HotelsFragment


class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupToolbarNavigation()
        replaceFragment(R.id.fragment_container, ::HotelsFragment)

    }

    inline fun replaceFragment(containerViewId: Int, f: () -> Fragment): Fragment? {
        return f().apply {
            if (this !is HotelsFragment)
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

    private val onFragmentManagerBackstackChanged = {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener { view -> onBackPressed() }
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    fun setupToolbarNavigation() {
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24px)
        supportFragmentManager?.addOnBackStackChangedListener { onFragmentManagerBackstackChanged() }
    }
}
