package com.ugurbuga.followtvmovie.domain.person.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.person.PersonRepository
import com.ugurbuga.followtvmovie.domain.person.mapper.PersonMapper
import com.ugurbuga.followtvmovie.domain.person.model.PersonDetailUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPersonDetailUseCase @Inject constructor(
    private val personRepository: PersonRepository, private val personMapper: PersonMapper
) : UseCase<GetPersonDetailUseCase.PersonDetailParam, PersonDetailUIModel>() {

    data class PersonDetailParam(val personId: String)

    override fun execute(params: PersonDetailParam): Flow<ApiState<PersonDetailUIModel>> {
        return personRepository.getPersonDetail(params.personId).map {
            it.map { personDetailResponse ->
                personMapper.toPersonDetailUIModel(personDetailResponse)
            }
        }
    }
}