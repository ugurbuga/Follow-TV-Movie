package com.ugurbuga.followtvmovie.watch.ui.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.watch.base.ListAdapterItem
import com.ugurbuga.followtvmovie.watch.domain.comingsoon.UpcomingMovieUseCase
import com.ugurbuga.followtvmovie.watch.ui.popularlist.model.LoadingUIModel
import com.ugurbuga.followtvmovie.watch.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
                    ++page
                )
            ).collect {
                when (it) {
                    is Resource.Error -> {
                    }
                    Resource.Loading -> {
                        val list = comingSoonMovies.value.toMutableList()
                        list.add(LoadingUIModel())
                        _comingSoonMovies.value = list
                    }
                    is Resource.Success -> {
                        val list = comingSoonMovies.value.toMutableList()
                        val newList = ArrayList(list.filter { item -> item !is LoadingUIModel })
                        newList.addAll(it.data.results)
                        _comingSoonMovies.value = newList
                        isCanLoadNewItemPopularMovie = it.data.totalPages > it.data.page
                    }
                }
            }
        }
    }

    fun addLoadingAndGetNewItems() {
        if (isCanLoadNewItemPopularMovie) {
            isCanLoadNewItemPopularMovie = false
            getMovies()
        }
    }
}