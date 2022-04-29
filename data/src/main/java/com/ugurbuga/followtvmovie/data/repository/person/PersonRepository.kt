package com.ugurbuga.followtvmovie.data.repository.person

import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.data.model.response.credit.CreditResponse
import com.ugurbuga.followtvmovie.data.model.response.image.PersonImageResponse
import com.ugurbuga.followtvmovie.data.model.response.person.PersonDetailResponse
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    fun getPersonDetail(page: String): Flow<ApiState<PersonDetailResponse>>

    fun getPersonImages(personId: String): Flow<ApiState<PersonImageResponse>>

    fun getPersonCredits(personId: String): Flow<ApiState<CreditResponse>>
}
