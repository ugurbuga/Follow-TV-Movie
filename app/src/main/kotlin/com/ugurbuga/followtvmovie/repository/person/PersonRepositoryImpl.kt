package com.ugurbuga.followtvmovie.repository.person

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.data.api.services.PersonService
import com.ugurbuga.followtvmovie.domain.credit.model.CreditResponse
import com.ugurbuga.followtvmovie.domain.image.model.PersonImageResponse
import com.ugurbuga.followtvmovie.domain.person.model.PersonDetailResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PersonRepositoryImpl @Inject constructor(
    private val personService: PersonService
) :
    PersonRepository, FTMBaseRepository() {

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
