package com.ugurbuga.followtvmovie.data.repository.person

import com.ugurbuga.followtvmovie.core.base.BaseRepository
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.data.api.services.PersonService
import com.ugurbuga.followtvmovie.data.model.response.credit.CreditResponse
import com.ugurbuga.followtvmovie.data.model.response.image.PersonImageResponse
import com.ugurbuga.followtvmovie.data.model.response.person.PersonDetailResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val personService: PersonService
) :
    PersonRepository, BaseRepository() {

    override fun getPersonDetail(personId: String): Flow<ApiState<PersonDetailResponse>> {
        return onApiCall { personService.getPersonDetail(personId) }
    }

    override fun getPersonImages(personId: String): Flow<ApiState<PersonImageResponse>> {
        return onApiCall { personService.getPersonImages(personId) }
    }

    override fun getPersonCredits(personId: String): Flow<ApiState<CreditResponse>> {
        return onApiCall { personService.getPersonCredits(personId) }
    }
}
