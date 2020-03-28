package com.example.watchit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.watchit.moviesFragment.MoviesFragment
import com.example.watchit.moviesFragment.favorites.FavoritesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

            launchFragment(MoviesFragment())


        val bottomNavigationMenu = findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationMenu.setOnNavigationItemSelectedListener {
            val fragment: Fragment
            when (it.itemId) {
                R.id.top_rated -> {
                    fragment = MoviesFragment.newInstance("top_rated")
                    launchFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.up_comming -> {
                    fragment = MoviesFragment.newInstance("upcoming")
                    launchFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.now_playing -> {
                    fragment = MoviesFragment.newInstance("now_playing")
                    launchFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.favorites -> {
                    fragment = FavoritesFragment()
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
        transaction.commit()

    }
}
