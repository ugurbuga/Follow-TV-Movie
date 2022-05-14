package com.ugurbuga.followtvmovie.domain.person.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.person.PersonRepository
import com.ugurbuga.followtvmovie.domain.credit.mapper.CreditMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPersonKnownForUseCase @Inject constructor(
    private val personRepository: PersonRepository,
    private val creditMapper: CreditMapper
) : UseCase<GetPersonKnownForUseCase.PersonKnownForParams, ArrayList<PosterItemUIModel>>() {

    data class PersonKnownForParams(val personId: String)

    override fun execute(params: PersonKnownForParams): Flow<ApiState<ArrayList<PosterItemUIModel>>> {
        return personRepository.getPersonCredits(params.personId).map {
            it.map { creditResponse ->
                creditMapper.toPosterList(creditResponse)
            }
        }
    }
}