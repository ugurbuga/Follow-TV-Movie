package com.ugurbuga.followtvmovie.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.viewbinding.BuildConfig
import com.ugurbuga.followtvmovie.core.base.BaseViewModel
import com.ugurbuga.followtvmovie.core.common.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class FTMBaseViewModel : BaseViewModel() {

    private val _baseEvent = MutableSharedFlow<FTMBaseViewEvent>()
    val baseEvent: SharedFlow<FTMBaseViewEvent> = _baseEvent

    private val _baseViewState = MutableLiveData<FTMBaseViewState>()
    val baseViewState: LiveData<FTMBaseViewState> = _baseViewState

    fun initStatusState(
        status: Status,
        isShowLoading: Boolean = true,
        isShowErrorMessage: Boolean = true,
        errorId: Int? = null,
    ) {
        when (status) {
            is Status.Loading -> {
                if (BuildConfig.DEBUG) {
                    Log.d("STATE ->", "LOADING")
                }
                showLoading(isShowLoading)
            }

            is Status.Error -> {
                if (BuildConfig.DEBUG) {
                    Log.d("STATE ->", "ERROR ${status.message}")
                }
                dismissLoading(isShowLoading)

                if (isShowErrorMessage) {
                    checkError(status.message, status.code, errorId)
                }
            }

            is Status.Success -> {
                if (BuildConfig.DEBUG) {
                    Log.d("STATE ->", "SUCCESS")
                }
                dismissLoading(isShowLoading)
            }
        }
    }

    private fun dismissLoading(isShowLoading: Boolean) {
        if (isShowLoading) {
            dismissLoading()
        }
    }

    private fun showLoading(isShowLoading: Boolean) {
        if (isShowLoading) {
            showLoading()
        }
    }

    private fun checkError(message: Any, code: Int, errorId: Int?) {
        showErrorMessage(message, errorId)
    }

    private fun showLoading() {
        _baseEvent.emitSuspending(FTMBaseViewEvent.ShowLoading)
    }

    private fun dismissLoading() {
        _baseEvent.emitSuspending(FTMBaseViewEvent.DismissLoading)

    }

    private fun showErrorMessage(message: Any, errorId: Int? = null) {
        _baseEvent.emitSuspending(FTMBaseViewEvent.ShowErrorMessage(message, errorId))

    }

    fun <T> MutableSharedFlow<T>.emitSuspending(value: T) =
        viewModelScope.launch(Dispatchers.IO) { emit(value) }
}
