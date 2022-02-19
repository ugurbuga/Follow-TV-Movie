package com.ugurbuga.followtvmovie.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.viewbinding.BuildConfig
import com.ugurbuga.followtvmovie.base.base.BaseViewModel
import com.ugurbuga.followtvmovie.common.Status
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
                    Log.d("STATE ->", "ERROR ${status.exception?.message}")
                }
                dismissLoading(isShowLoading)

                if (isShowErrorMessage) {
                    GeneralErrorsHandler(
                        { message, code ->
                            checkError(message, code, errorId)
                        }, status.exception!!
                    )
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
        showErrorMessage(
            message, errorId
        )
    }

    private fun showLoading() {
        viewModelScope.launch {
            _baseEvent.emit(FTMBaseViewEvent.ShowLoading)
        }
    }

    private fun dismissLoading() {
        viewModelScope.launch {
            _baseEvent.emit(FTMBaseViewEvent.DismissLoading)
        }
    }

    private fun showErrorMessage(message: Any, errorId: Int? = null) {
        viewModelScope.launch {
            _baseEvent.emit(FTMBaseViewEvent.ShowErrorMessage(message, errorId))
        }
    }
}
