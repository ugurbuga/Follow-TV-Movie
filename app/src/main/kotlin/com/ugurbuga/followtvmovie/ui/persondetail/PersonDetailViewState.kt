package com.ugurbuga.followtvmovie.ui.persondetail

import com.ugurbuga.followtvmovie.domain.image.model.ImageUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.domain.person.model.PersonDetailUIModel

data class PersonDetailViewState(
    val personDetail: PersonDetailUIModel? = null,
    val images: ArrayList<ImageUIModel> = arrayListOf(),
    val casts: ArrayList<CastUIModel> = arrayListOf(),
)