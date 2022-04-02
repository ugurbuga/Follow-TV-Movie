package com.ugurbuga.followtvmovie.watch.ui.popularlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.watch.domain.popularlist.PopularMovieUseCase
import com.ugurbuga.followtvmovie.watch.base.ListAdapterItem
import com.ugurbuga.followtvmovie.watch.ui.popularlist.model.LoadingUIModel
import com.ugurbuga.followtvmovie.watch.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
                    ++page
                )
            ).collect {
                when (it) {
                    is Resource.Error -> {
                    }
                    Resource.Loading -> {
                        val list = popularMovies.value.toMutableList()
                        list.add(LoadingUIModel())
                        _popularMovies.value = list
                    }
                    is Resource.Success -> {
                        val list = popularMovies.value.toMutableList()
                        val newList = ArrayList(list.filter { item -> item !is LoadingUIModel })
                        newList.addAll(it.data.results)
                        _popularMovies.value = newList
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