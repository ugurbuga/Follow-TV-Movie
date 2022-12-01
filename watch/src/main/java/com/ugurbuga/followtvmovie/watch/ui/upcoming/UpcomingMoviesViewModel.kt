package com.ugurbuga.followtvmovie.watch.ui.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.core.extensions.doOnLoading
import com.ugurbuga.followtvmovie.core.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.domain.popular.movie.usecase.UpcomingMovieUseCase
import com.ugurbuga.followtvmovie.domain.poster.model.LoadingUIModel
import com.ugurbuga.followtvmovie.watch.ui.detail.MediaType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    private val upcomingMovieUseCase: UpcomingMovieUseCase
) : ViewModel() {

    private val _comingSoonMovies = MutableStateFlow(mutableListOf<ListAdapterItem>())
    val comingSoonMovies: StateFlow<MutableList<ListAdapterItem>> get() = _comingSoonMovies

    var page = 0
    private var isCanLoadNewItemPopularMovie = false

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            upcomingMovieUseCase(
                UpcomingMovieUseCase.UpcomingMovieParams(
                    MediaType.MOVIE,
                    ++page
                )
            ).doOnLoading {
                val list = comingSoonMovies.value.toMutableList()
                list.add(LoadingUIModel())
                _comingSoonMovies.value = list

            }.doOnSuccess {
                val list = comingSoonMovies.value.toMutableList()
                val newList = ArrayList(list.filter { item -> item !is LoadingUIModel })
                newList.addAll(it.posterList)
                _comingSoonMovies.value = newList
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