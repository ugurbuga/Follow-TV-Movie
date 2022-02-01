package com.ugurbuga.followtvmovie.view.toolbar

sealed class ToolbarViewState {

    data class ToolbarProperties(
        val navigationIconType: FTMToolbar.NavigationIconType,
        val title: String
    ) : ToolbarViewState()

    object NoToolbar : ToolbarViewState()
}
