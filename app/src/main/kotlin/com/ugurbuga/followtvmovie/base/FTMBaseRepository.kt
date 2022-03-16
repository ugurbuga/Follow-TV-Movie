package com.ugurbuga.followtvmovie.base

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
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
            recordException(error)
            emit(Resource.Error(error))
        }.flowOn(dispatcher)

    private fun recordException(error: Throwable) {
        Firebase.crashlytics.recordException(error)
    }

    fun <T : Any> onRoomCall(call: suspend () -> T): Flow<Resource<T>> =
        flow {
            emit(Resource.Loading)
            emit(Resource.Success(data = call.invoke()))
        }.catch { error ->
            if (BuildConfig.DEBUG) {
                error.printStackTrace()
            }
            recordException(error)
            emit(Resource.Error(error))
        }.flowOn(dispatcher)

    fun <T : Any?> onRoomFlowCall(call: Flow<T>): Flow<Resource<T>> =
        flow {
            emit(Resource.Loading)
            call.collect {
                emit(Resource.Success(it))
            }
        }.catch {
            recordException(Throwable("onRoomFlowCall"))
            emit(Resource.Error(it))
        }.flowOn(dispatcher)
}
