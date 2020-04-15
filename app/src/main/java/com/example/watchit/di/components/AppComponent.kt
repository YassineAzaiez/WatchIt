package com.example.watchit.di.components

import androidx.fragment.app.Fragment
import com.example.core.di.Componants.CoreComponent
import com.example.watchit.di.modules.MovieScope
import com.example.watchit.di.modules.MoviesModule
import com.example.watchit.moviesFragment.MoviesFragment
import com.example.watchit.moviesFragment.favorites.FavoritesFragment
import com.example.watchit.search.SearchFragment
import dagger.Component

@MovieScope
@Component(modules = [MoviesModule::class],dependencies = [CoreComponent::class])
interface AppComponent {
 fun inject(moviesFragment: MoviesFragment)
 fun inject (favoritesFragment: FavoritesFragment)
 fun inject(searchFragment: SearchFragment)



}