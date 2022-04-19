package com.ugurbuga.followtvmovie.ui.trailer

import androidx.navigation.fragment.navArgs
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentTrailerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrailerFragment : FTMBaseVMFragment<TrailerViewModel, FragmentTrailerBinding>() {

    val args: TrailerFragmentArgs by navArgs()

    override fun getResourceLayoutId() = R.layout.fragment_trailer

    override fun viewModelClass() = TrailerViewModel::class.java

    override fun onInitDataBinding() {
        showLoading()
        lifecycle.addObserver(viewBinding.youtubePlayerView)

        viewBinding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(args.urlKey, 0f)
            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                super.onError(youTubePlayer, error)
                dismissLoading()
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState
            ) {
                super.onStateChange(youTubePlayer, state)
                if (state == PlayerConstants.PlayerState.PLAYING || state == PlayerConstants.PlayerState.BUFFERING) {
                    dismissLoading()
                }
            }
        })
    }
}