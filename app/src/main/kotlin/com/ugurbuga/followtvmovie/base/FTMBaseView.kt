package com.ugurbuga.followtvmovie.base

interface FTMBaseView {

    fun onViewEvent(baseViewEvent: FTMBaseViewEvent)

    fun showErrorDialog(message: Any, errorId: Int? = null)

    fun showLoading()

    fun dismissLoading()

}
