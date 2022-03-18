package com.ugurbuga.followtvmovie.watch.repository

import com.ugurbuga.followtvmovie.watch.BuildConfig
import com.ugurbuga.followtvmovie.watch.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class FTMBaseRepository {

    var dispatcher: CoroutineDispatcher = Dispatchers.IO

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

    fun <T : Any> onRoomCall(call: suspend () -> T): Flow<Resource<T>> =
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
