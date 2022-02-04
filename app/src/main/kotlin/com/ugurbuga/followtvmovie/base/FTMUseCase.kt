package com.ugurbuga.followtvmovie.base

import kotlinx.coroutines.flow.Flow

abstract class FTMUseCase<Request, Response> {
    operator fun invoke(params: Request): Flow<Response> = execute(params)

    protected abstract fun execute(params: Request): Flow<Response>
}
