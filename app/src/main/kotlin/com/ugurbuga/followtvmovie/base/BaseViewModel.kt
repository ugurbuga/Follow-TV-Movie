package com.ugurbuga.followtvmovie.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewbinding.BuildConfig
import com.ugurbuga.followtvmovie.common.Event
import com.ugurbuga.followtvmovie.common.Status
import com.ugurbuga.followtvmovie.view.loading.LoadingType

abstract class BaseViewModel : ViewModel() {

    private val _baseEvent = MutableLiveData<Event<BaseViewEvent>>()
    val baseEvent: LiveData<Event<BaseViewEvent>> = _baseEvent

    private val _baseViewState = MutableLiveData<BaseViewState>()
    val baseViewState: LiveData<BaseViewState> = _baseViewState

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
                        },
                        status.exception!!
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
            message,
            errorId
        )
    }

    private fun showLoading() {
        _baseEvent.value = Event(BaseViewEvent.ShowLoading)
    }

    private fun dismissLoading() {
        _baseEvent.value = Event(BaseViewEvent.DismissLoading)
    }

    private fun showErrorMessage(message: Any, errorId: Int? = null) {
        _baseEvent.value =
            Event(BaseViewEvent.ShowErrorMessage(message, errorId))
    }
}
