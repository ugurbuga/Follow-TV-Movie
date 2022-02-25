package com.ugurbuga.followtvmovie.ui.person

import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel

sealed class PersonDetailViewEvent {

    data class NavigateToImages(val imageList: ArrayList<ImageUIModel>, val position: Int) :
        PersonDetailViewEvent()

}
