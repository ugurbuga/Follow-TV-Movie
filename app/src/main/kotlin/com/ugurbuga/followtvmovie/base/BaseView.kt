package com.ugurbuga.followtvmovie.base

interface BaseView {

    fun onViewEvent(baseViewEvent: BaseViewEvent)

    fun showErrorDialog(message: Any, errorId: Int? = null)

    fun showLoading()

    fun dismissLoading()

}
