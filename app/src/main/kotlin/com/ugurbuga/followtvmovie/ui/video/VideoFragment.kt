package com.ugurbuga.followtvmovie.ui.video

import androidx.navigation.fragment.navArgs
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentVideoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoFragment : FTMBaseVMFragment<VideoViewModel, FragmentVideoBinding>() {

    val args: VideoFragmentArgs by navArgs()

    override fun getResourceLayoutId() = R.layout.fragment_video

    override fun viewModelClass() = VideoViewModel::class.java

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