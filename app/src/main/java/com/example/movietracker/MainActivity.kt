package com.example.movietracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.movietracker.ui.HomeFragment
import com.example.movietracker.ui.ProfileFragment
import com.example.movietracker.ui.WatchlistFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> openFragment(HomeFragment(), "HomeFragment")
                R.id.nav_watchlist -> openFragment(WatchlistFragment(), "WatchlistFragment")
                R.id.nav_profile -> openFragment(ProfileFragment(), "ProfileFragment")
            }
            true
        }

        // Default Fragment
        if (savedInstanceState == null) {
            openFragment(HomeFragment(), "HomeFragment")
        }
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val currentFragment = fragmentManager.findFragmentById(R.id.nav_host_fragment)

        if (currentFragment != null) {
            when {
                currentFragment is HomeFragment -> {
                    fragmentTransaction.setCustomAnimations(
                        R.anim.slide_in_right, R.anim.slide_out_left
                    )
                }
                currentFragment is WatchlistFragment && fragment is ProfileFragment -> {
                    fragmentTransaction.setCustomAnimations(
                        R.anim.slide_in_right, R.anim.slide_out_left
                    )
                }
                currentFragment is WatchlistFragment && fragment is HomeFragment -> {
                    fragmentTransaction.setCustomAnimations(
                        R.anim.slide_in_left, R.anim.slide_out_right
                    )
                }
                currentFragment is ProfileFragment -> {
                    fragmentTransaction.setCustomAnimations(
                        R.anim.slide_in_left, R.anim.slide_out_right
                    )
                }
            }
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        }
        // Apply custom animation


        fragmentTransaction.replace(R.id.nav_host_fragment, fragment, tag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}