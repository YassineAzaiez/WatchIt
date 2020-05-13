package com.example.watchit


import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.watchit.moviesFragment.MoviesFragment
import com.example.watchit.moviesFragment.favorites.FavoritesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    companion object {
        const val FRAGMENT_NAME = "fragmentName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(this)
        val selectedItem = savedInstanceState?.getInt(FRAGMENT_NAME) ?: R.id.top_rated
        navigation.selectedItemId = selectedItem
        when (selectedItem) {
            R.id.up_comming -> launchFragment(MoviesFragment.newInstance("upcoming"))
            R.id.now_playing -> launchFragment(MoviesFragment.newInstance("now_playing"))
            else -> launchFragment(MoviesFragment())
        }


    }


    private fun launchFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(FRAGMENT_NAME, navigation.selectedItemId)
        Log.d("item id","is"+navigation.selectedItemId)
        super.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val fragment: Fragment
        when (p0.itemId) {
            R.id.top_rated -> {
                fragment = MoviesFragment.newInstance("top_rated")
                launchFragment(fragment)
            }

            R.id.up_comming -> {
                fragment = MoviesFragment.newInstance("upcoming")
                launchFragment(fragment)

            }

            R.id.now_playing -> {
                fragment = MoviesFragment.newInstance("now_playing")
                launchFragment(fragment)

            }
            R.id.favorites -> {
                fragment = FavoritesFragment()
                launchFragment(fragment)
            }
        }
        return true
    }
}