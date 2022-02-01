package com.ugurbuga.followtvmovie.base

import com.ugurbuga.followtvmovie.BuildConfig
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

abstract class FTMBaseRepository {

    @Inject
    @IoDispatcher
    lateinit var dispatcher: CoroutineDispatcher

    fun <T : Any> onApiCall(call: suspend () -> T): Flow<Resource<T>> =
        flow {
            emit(Resource.Loading)
            emit(Resource.Success(data = call.invoke()))
        }.catch { error ->
            if (BuildConfig.DEBUG) {
                error.printStackTrace()
            }
            emit(Resource.Error(error))
        }.flowOn(dispatcher)
}
