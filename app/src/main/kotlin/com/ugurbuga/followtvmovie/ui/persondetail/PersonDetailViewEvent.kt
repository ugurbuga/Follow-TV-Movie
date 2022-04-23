package com.ugurbuga.followtvmovie.ui.persondetail

import com.ugurbuga.followtvmovie.domain.image.model.ImageUIModel

sealed class PersonDetailViewEvent {

    data class NavigateToImages(val imageList: ArrayList<ImageUIModel>, val position: Int) :
        PersonDetailViewEvent()

}
