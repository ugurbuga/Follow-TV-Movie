package com.ugurbuga.followtvmovie.extensions

import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.MimeTypes

fun PlayerView.initializeExoPlayer(
    url: String, position: Long = 0L
) {
    if (player == null) {
        player = ExoPlayer.Builder(context).build()
    }
    setNewPlayer(url, position)
}

private fun PlayerView.setNewPlayer(url: String, position: Long = 0L) {
    val mimeType = MimeTypes.APPLICATION_MP4

    val mediaItem = MediaItem.Builder().setUri(url).setMimeType(mimeType).build()
    player?.repeatMode = ExoPlayer.REPEAT_MODE_ONE
    player?.setMediaItem(mediaItem)
    player?.playWhenReady = true
    player?.seekTo(position)
    player?.prepare()
}

fun PlayerView.releasePlayer(): Long {
    var position = 0L
    player?.run {
        position = this.currentPosition
        playWhenReady = false
        //removeListener(listener)
        release()
    }
    player = null
    return position
}

fun PlayerView.muteExoPlayer() {
    player?.volume = 0f
}

fun PlayerView.currentVolume(): Float {
    return player?.volume ?: 0f
}

fun PlayerView.unMuteExoPlayer() {
    player?.volume = 1f
}