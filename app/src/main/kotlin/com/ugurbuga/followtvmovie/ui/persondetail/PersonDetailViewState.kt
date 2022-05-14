package com.ugurbuga.followtvmovie.ui.persondetail

import com.ugurbuga.followtvmovie.domain.image.model.ImageUIModel
import com.ugurbuga.followtvmovie.domain.person.model.PersonDetailUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel

data class PersonDetailViewState(
    val personDetail: PersonDetailUIModel? = null,
    val images: ArrayList<ImageUIModel> = arrayListOf(),
    val posters: ArrayList<PosterItemUIModel> = arrayListOf(),
)