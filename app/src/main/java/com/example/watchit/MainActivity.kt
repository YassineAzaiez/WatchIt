package com.example.watchit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.watchit.nowplayingMovies.NowPlayingFragment
import com.example.watchit.topRadtedMovies.TopRatedMoviesFragment
import com.example.watchit.upcommingMovies.UpCommingMoviesFragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launchFragment(TopRatedMoviesFragment())
        val bottomNavigationMenu = findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationMenu.setOnNavigationItemSelectedListener {
            val fragment: Fragment
            when (it.itemId) {
                R.id.top_rated -> {
                    fragment =
                        TopRatedMoviesFragment()
                    launchFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.up_comming -> {
                    fragment = UpCommingMoviesFragment()
                    launchFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.now_playing -> {
                    fragment = NowPlayingFragment()
                    launchFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false

        }

    }

    private fun launchFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }
}
