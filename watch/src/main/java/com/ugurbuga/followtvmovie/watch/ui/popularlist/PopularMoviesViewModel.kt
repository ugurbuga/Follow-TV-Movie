package com.ugurbuga.followtvmovie.watch.ui.popularlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.core.extensions.doOnLoading
import com.ugurbuga.followtvmovie.core.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.domain.popular.movie.usecase.PopularMovieUseCase
import com.ugurbuga.followtvmovie.domain.poster.model.LoadingUIModel
import com.ugurbuga.followtvmovie.watch.ui.detail.MediaType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val popularMovieUseCase: PopularMovieUseCase
) : ViewModel() {

    private val _popularMovies = MutableStateFlow(mutableListOf<ListAdapterItem>())
    val popularMovies: StateFlow<MutableList<ListAdapterItem>> get() = _popularMovies

    var page = 0
    private var isCanLoadNewItemPopularMovie = false

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            popularMovieUseCase(
                PopularMovieUseCase.PopularMovieParams(
                    MediaType.MOVIE,
                    ++page
                )
            ).doOnLoading {
                val list = popularMovies.value.toMutableList()
                list.add(LoadingUIModel())
                _popularMovies.value = list
            }.doOnSuccess {
                val list = popularMovies.value.toMutableList()
                val newList = ArrayList(list.filter { item -> item !is LoadingUIModel })
                newList.addAll(it.posterList)
                _popularMovies.value = newList
                isCanLoadNewItemPopularMovie = it.totalPages > it.page
            }.launchIn(viewModelScope)
        }
    }

    fun addLoadingAndGetNewItems() {
        if (isCanLoadNewItemPopularMovie) {
            isCanLoadNewItemPopularMovie = false
            getMovies()
        }
    }
}