package com.ugurbuga.followtvmovie.domain.person.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.image.ImageMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel
import com.ugurbuga.followtvmovie.repository.person.PersonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPersonImagesUseCase @Inject constructor(
    private val personRepository: PersonRepository, private val imageMapper: ImageMapper
) : FTMUseCase<GetPersonImagesUseCase.PersonImagesParam, ArrayList<ImageUIModel>>() {

    data class PersonImagesParam(val personId: String)

    override fun execute(params: PersonImagesParam): Flow<ApiState<ArrayList<ImageUIModel>>> {
        return personRepository.getPersonImages(params.personId).map {
            it.map { personDetailResponse ->
                imageMapper.toPersonImageList(personDetailResponse)
            }
        }
    }
}