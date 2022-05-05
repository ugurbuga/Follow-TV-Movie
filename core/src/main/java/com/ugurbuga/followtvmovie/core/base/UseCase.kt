package com.ugurbuga.followtvmovie.core.base

import com.ugurbuga.followtvmovie.core.common.ApiState
import kotlinx.coroutines.flow.Flow

abstract class UseCase<Request, Response> {
    operator fun invoke(params: Request): Flow<ApiState<Response>> = execute(params)

    protected abstract fun execute(params: Request): Flow<ApiState<Response>>
}
