package com.ugurbuga.followtvmovie.ui.person

import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.domain.person.model.PersonDetailUIModel

data class PersonDetailViewState(
    val personDetail: PersonDetailUIModel? = null,
    val images: ArrayList<ImageUIModel> = arrayListOf(),
    val casts: ArrayList<CastUIModel> = arrayListOf(),
)