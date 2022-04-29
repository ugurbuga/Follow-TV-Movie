package com.ugurbuga.followtvmovie.ui.tvshows

import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor() : FTMBaseViewModel() {

    private val _query = MutableStateFlow(com.ugurbuga.followtvmovie.core.common.Util.EMPTY_STRING)
    val query: StateFlow<String> get() = _query

    fun setQuery(query: String) {
        _query.value = query
    }

}