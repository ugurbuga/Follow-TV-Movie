package com.ugurbuga.followtvmovie.repository.person

import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.data.model.response.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.image.model.PersonImageResponse
import com.ugurbuga.followtvmovie.domain.person.model.PersonDetailResponse
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    fun getPersonDetail(page: String): Flow<ApiState<PersonDetailResponse>>

    fun getPersonImages(personId: String): Flow<ApiState<PersonImageResponse>>

    fun getPersonCredits(personId: String): Flow<ApiState<CreditResponse>>
}
