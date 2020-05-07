package com.example.watchit.utils

import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

abstract class AppBarStateChangeListener : AppBarLayout.OnOffsetChangedListener {
    enum class AppbarState {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private var mCurrentState = AppbarState.IDLE

    override fun onOffsetChanged(appbar: AppBarLayout, p: Int) {

        when {
            p == 0 -> {
                if (mCurrentState != AppbarState.EXPANDED) {
                    onStateChanged(appbar, AppbarState.EXPANDED)
                }
                mCurrentState = AppbarState.EXPANDED
            }
            abs(p) >= appbar.totalScrollRange -> {
                if (mCurrentState != AppbarState.COLLAPSED) {
                    onStateChanged(appbar, AppbarState.COLLAPSED)
                }
                mCurrentState = AppbarState.COLLAPSED
            }
            else -> {
                if (mCurrentState != AppbarState.IDLE) {
                    onStateChanged(appbar, AppbarState.IDLE)
                }
                mCurrentState = AppbarState.IDLE
            }
        }

    }

    abstract fun onStateChanged(appBarLayout: AppBarLayout?, state: AppbarState)
}