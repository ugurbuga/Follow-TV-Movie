package com.ugurbuga.followtvmovie.watch.repository

import com.ugurbuga.followtvmovie.watch.util.Resource
import kotlinx.coroutines.flow.Flow

abstract class FTMUseCase<Request, Response> {
    operator fun invoke(params: Request): Flow<Resource<Response>> = execute(params)

    protected abstract fun execute(params: Request): Flow<Resource<Response>>
}
