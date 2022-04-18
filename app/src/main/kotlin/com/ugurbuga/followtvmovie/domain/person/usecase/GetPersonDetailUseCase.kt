package com.ugurbuga.followtvmovie.domain.person.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.person.mapper.PersonMapper
import com.ugurbuga.followtvmovie.domain.person.model.PersonDetailUIModel
import com.ugurbuga.followtvmovie.repository.person.PersonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPersonDetailUseCase @Inject constructor(
    private val personRepository: PersonRepository, private val personMapper: PersonMapper
) : FTMUseCase<GetPersonDetailUseCase.PersonDetailParam, PersonDetailUIModel>() {

    data class PersonDetailParam(val personId: String)

    override fun execute(params: PersonDetailParam): Flow<ApiState<PersonDetailUIModel>> {
        return personRepository.getPersonDetail(params.personId).map {
            it.map { personDetailResponse ->
                personMapper.toPersonDetailUIModel(personDetailResponse)
            }
        }
    }
}