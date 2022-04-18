package com.ugurbuga.followtvmovie.domain.person.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.mapper.CreditMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.repository.person.PersonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPersonCastsUseCase @Inject constructor(
    private val personRepository: PersonRepository,
    private val creditMapper: CreditMapper
) : FTMUseCase<GetPersonCastsUseCase.PersonCastParams, ArrayList<CastUIModel>>() {

    data class PersonCastParams(val personId: String)

    override fun execute(params: PersonCastParams): Flow<ApiState<ArrayList<CastUIModel>>> {
        return personRepository.getPersonCredits(params.personId).map {
            it.map { creditResponse ->
                creditMapper.toCastList(creditResponse)
            }
        }
    }
}