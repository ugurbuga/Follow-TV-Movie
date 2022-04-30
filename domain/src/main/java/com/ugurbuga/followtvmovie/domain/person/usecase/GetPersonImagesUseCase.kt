package com.ugurbuga.followtvmovie.domain.person.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.person.PersonRepository
import com.ugurbuga.followtvmovie.domain.image.mapper.ImageMapper
import com.ugurbuga.followtvmovie.domain.image.model.ImageUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPersonImagesUseCase @Inject constructor(
    private val personRepository: PersonRepository, private val imageMapper: ImageMapper
) : UseCase<GetPersonImagesUseCase.PersonImagesParam, ArrayList<ImageUIModel>>() {

    data class PersonImagesParam(val personId: String)

    override fun execute(params: PersonImagesParam): Flow<ApiState<ArrayList<ImageUIModel>>> {
        return personRepository.getPersonImages(params.personId).map {
            it.map { personDetailResponse ->
                imageMapper.toPersonImageList(personDetailResponse)
            }
        }
    }
}