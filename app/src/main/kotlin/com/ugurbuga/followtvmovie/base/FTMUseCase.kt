package com.ugurbuga.followtvmovie.base

import com.ugurbuga.followtvmovie.common.ApiState
import kotlinx.coroutines.flow.Flow

abstract class FTMUseCase<Request, Response> {
    operator fun invoke(params: Request): Flow<ApiState<Response>> = execute(params)

    protected abstract fun execute(params: Request): Flow<ApiState<Response>>
}
