package com.example.watchit.di.components

import androidx.fragment.app.Fragment
import com.example.core.di.Componants.CoreComponent
import com.example.watchit.di.modules.MovieScope
import com.example.watchit.di.modules.MoviesModule
import com.example.watchit.nowplayingMovies.NowPlayingFragment
import com.example.watchit.topRadtedMovies.TopRatedMoviesFragment
import com.example.watchit.topRadtedMovies.TopRatedMoviesViewModel
import com.example.watchit.upcommingMovies.UpCommingMoviesFragment
import dagger.Component

@MovieScope
@Component(modules = [MoviesModule::class],dependencies = [CoreComponent::class])
interface AppComponent {
 fun inject(moviesFragment: UpCommingMoviesFragment)
 fun inject(moviesFragment: NowPlayingFragment)
 fun inject(moviesFragment: TopRatedMoviesFragment)


}