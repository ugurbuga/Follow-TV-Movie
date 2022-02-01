package com.ugurbuga.followtvmovie.base

sealed class FTMBaseViewEvent {

    object ShowLoading : FTMBaseViewEvent()

    object DismissLoading : FTMBaseViewEvent()

    data class ShowErrorMessage(val message: Any, val errorId: Int? = null) : FTMBaseViewEvent()

}
