package com.ugurbuga.followtvmovie.domain.moviedetail.mapper

import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.VideoUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.video.VideosResponse
import javax.inject.Inject

class VideoMapper @Inject constructor() {

    companion object {
        private const val YOUTUBE = "YouTube"
    }

    fun toVideoList(response: VideosResponse): ArrayList<VideoUIModel> {
        val list = arrayListOf<VideoUIModel>()
        response.results.forEach {
            if (it.site == YOUTUBE) {
                list.add(VideoUIModel(key = it.key, name = it.name))
            }
        }
        return list
    }
}
