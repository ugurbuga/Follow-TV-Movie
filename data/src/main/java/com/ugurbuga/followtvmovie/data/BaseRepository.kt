package com.ugurbuga.followtvmovie.data

import com.ugurbuga.followtvmovie.core.BuildConfig
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.di.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {

    @Inject
    @IoDispatcher
    lateinit var dispatcher: CoroutineDispatcher

    fun <T : Any> onApiCall(call: suspend () -> T): Flow<ApiState<T>> =
        flow {
            emit(ApiState.Loading)
            emit(ApiState.Success(data = call.invoke()))
        }.catch { error ->
            if (BuildConfig.DEBUG) {
                error.printStackTrace()
            }
            recordException(error)
            val errorPackage = GeneralErrorsHandler.convertError(error)
            emit(ApiState.Error(errorPackage.first, errorPackage.second))
        }.flowOn(dispatcher)

    private fun recordException(error: Throwable) {
        //Firebase.crashlytics.recordException(error)
    }

    fun <T : Any> onRoomCall(call: suspend () -> T): Flow<ApiState<T>> =
        flow {
            emit(ApiState.Loading)
            emit(ApiState.Success(data = call.invoke()))
        }.catch { error ->
            if (BuildConfig.DEBUG) {
                error.printStackTrace()
            }
            recordException(error)
            val errorPackage = GeneralErrorsHandler.convertError(error)
            emit(ApiState.Error(errorPackage.first, errorPackage.second))
        }.flowOn(dispatcher)

    fun <T : Any?> onRoomFlowCall(call: Flow<T>): Flow<ApiState<T>> =
        flow {
            emit(ApiState.Loading)
            call.collect {
                emit(ApiState.Success(it))
            }
        }.catch {
            recordException(Throwable("onRoomFlowCall"))
            val errorPackage = GeneralErrorsHandler.convertError(it)
            emit(ApiState.Error(errorPackage.first, errorPackage.second))
        }.flowOn(dispatcher)
}
