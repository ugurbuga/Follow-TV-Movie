package com.ugurbuga.followtvmovie.base

import com.ugurbuga.followtvmovie.common.Resource
import kotlinx.coroutines.flow.Flow

abstract class FTMResourceUseCase<Request, Response> {
    operator fun invoke(params: Request): Flow<Resource<Response>> = execute(params)

    protected abstract fun execute(params: Request): Flow<Resource<Response>>
}
