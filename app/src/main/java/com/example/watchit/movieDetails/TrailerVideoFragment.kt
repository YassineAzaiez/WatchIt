package com.example.watchit.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.watchit.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.dialog_fragment_trailer.*

/**
 * A simple [Fragment] subclass.
 */
class TrailerVideoFragment : BottomSheetDialogFragment() {
    companion object {
        const val VIDEOID = "videoId"
        fun newInstance(trailerId: String): TrailerVideoFragment {
            var args = Bundle()
            args.putString(VIDEOID, trailerId)
            val fragment = TrailerVideoFragment()
            fragment.arguments = args
            return fragment

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_trailer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(playerView)
        playerView.enableAutomaticInitialization = false
        playerView.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(arguments?.getString(VIDEOID,"")!!,0F)
            }
        },true)

    }


}


