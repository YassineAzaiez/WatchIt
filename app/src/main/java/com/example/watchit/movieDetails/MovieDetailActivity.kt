package com.example.watchit.movieDetails

import android.annotation.SuppressLint
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.service.autofill.Validators.or
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.domain.Movie
import com.example.watchit.R
import com.example.watchit.moviesFragment.MoviesFragment
import com.example.watchit.utils.AppBarStateChangeListener
import com.google.android.material.appbar.AppBarLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movie: Movie


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        movie = intent.getSerializableExtra(MoviesFragment.EXTRA_ITEM) as Movie
        supportFragmentManager.beginTransaction()
            .replace(container.id,MovieDetailsFragment.newInstance(movie))
            .commit()

        TitleToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        setSupportActionBar(TitleToolbar)
        TitleToolbar.setNavigationOnClickListener { finish() }
        TitleToolbar.title = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = ContextCompat.getColor(window.context,R.color.transparent40)
            }
        }

        appBar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: AppbarState) {
                movieName.text = if (state === AppbarState.COLLAPSED) movie.title else null
                Log.d("state","is : ${state.name}")
               movieName.isSelected = state == AppbarState.COLLAPSED
            }


        })

        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(this, R.color.black_background))
        collapsingToolbar.setStatusBarScrimColor(ContextCompat.getColor(this,R.color.primary))
        movieName.text = movie.title
        if (!movie.backdropPath.isNullOrEmpty()) {
            Picasso.get().load(Uri.parse("https://image.tmdb.org/t/p/w500" + movie.backdropPath))
                .fit()
                .centerCrop()
                .into(MovieCover)
        }


    }
}
