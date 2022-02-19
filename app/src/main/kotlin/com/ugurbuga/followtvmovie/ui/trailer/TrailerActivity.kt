package com.ugurbuga.followtvmovie.ui.trailer

import androidx.navigation.navArgs
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVmDbActivity
import com.ugurbuga.followtvmovie.databinding.ActivityTrailerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrailerActivity : FTMBaseVmDbActivity<TrailerViewModel, ActivityTrailerBinding>() {

    val args: TrailerActivityArgs by navArgs()

    override fun getLayoutResourceId() = R.layout.activity_trailer

    override fun onInitDataBinding() {
        lifecycle.addObserver(viewBinding.youtubePlayerView)

        viewBinding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(args.argUrlKey, 0f)
            }
        })
    }
}

