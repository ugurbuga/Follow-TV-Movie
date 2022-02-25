package com.ugurbuga.followtvmovie.repository.person

import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.image.PersonImageResponse
import com.ugurbuga.followtvmovie.domain.person.model.PersonDetailResponse
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    fun getPersonDetail(page: String): Flow<Resource<PersonDetailResponse>>

    fun getPersonImages(personId: String): Flow<Resource<PersonImageResponse>>

    fun getPersonCredits(personId: String): Flow<Resource<CreditResponse>>
}
