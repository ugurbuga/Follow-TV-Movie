package com.ugurbuga.followtvmovie.ui.soon.list

import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.poster.model.EmptyUIModel
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.ui.soon.GetSoonMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class SoonListViewModel @Inject constructor(
    private val getSoonMoviesUseCase: GetSoonMoviesUseCase,
) : FTMBaseViewModel() {

    private val _soonListViewState = MutableStateFlow(SoonListViewState())
    val soonMovieListViewState: StateFlow<SoonListViewState>
        get() = _soonListViewState

    @Suppress("UNCHECKED_CAST")
    fun getSoonMovies() {
        getSoonMoviesUseCase(Util.EMPTY_STRING)
            .doOnSuccess {
                setList(it as ArrayList<ListAdapterItem>)
            }.launchIn(viewModelScope)
    }

    private fun setList(list: ArrayList<ListAdapterItem>) {
        if (list.isEmpty()) {
            setEmptyState()
        } else {
            _soonListViewState.value = SoonListViewState(list)
        }
    }

    private fun setEmptyState() {
        _soonListViewState.value = SoonListViewState(
            arrayListOf(
                EmptyUIModel(
                    message = R.string.empty_soon_movie_list
                )
            )
        )
    }
}