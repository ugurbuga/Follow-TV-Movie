package com.ugurbuga.followtvmovie.ui.discover.popularlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.popular.movie.usecase.PopularMovieUseCase
import com.ugurbuga.followtvmovie.domain.poster.model.LoadingUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.domain.popular.tvshow.usecase.PopularTvShowUseCase
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.ui.discover.popularlist.PopularListFragment.Companion.ARG_POPULAR_LIST_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class PopularListViewModel @Inject constructor(
    private val popularTvShowUseCase: PopularTvShowUseCase,
    private val popularMovieUseCase: PopularMovieUseCase,
    savedStateHandle: SavedStateHandle,
) : FTMBaseViewModel() {

    private val _posterList = MutableLiveData<MutableList<Any>>().apply { value = mutableListOf() }
    val posterList: LiveData<MutableList<Any>> get() = _posterList

    private var isCanLoadNewItem = false

    private val listType = savedStateHandle.get<String>(ARG_POPULAR_LIST_TYPE)
    var page = 0

    init {
        getPopularList()
    }

    private fun getPopularList() {
        page++
        addLoading()
        when (listType) {
            PopularListType.MOVIE -> {
                getPopularMovies()
            }
            PopularListType.TV_SHOW -> {
                getPopularTvShows()
            }
        }
    }

    private fun getPopularTvShows() {
        popularTvShowUseCase(PopularTvShowUseCase.PopularTvShowParams(page))
            .doOnStatusChanged {
                initStatusState(
                    it,
                    isShowLoading = false
                )
            }
            .doOnSuccess {
                setPopularList(it)
            }
            .launchIn(viewModelScope)
    }

    private fun getPopularMovies() {
        popularMovieUseCase(PopularMovieUseCase.PopularMovieParams(page))
            .doOnStatusChanged {
                initStatusState(
                    it,
                    isShowLoading = false
                )
            }
            .doOnSuccess {
                setPopularList(it)
            }
            .launchIn(viewModelScope)
    }

    private fun addLoading() {
        val oldList = getOldList()
        oldList.add(LoadingUIModel())
        _posterList.value = oldList
    }

    private fun getOldList(): MutableList<Any> {
        return posterList.value ?: mutableListOf()
    }

    private fun setPopularList(posterModel: PosterUIModel) {
        val oldList = getOldList()
        oldList.remove(LoadingUIModel())
        oldList.addAll(posterModel.posterList)
        _posterList.value = oldList
        isCanLoadNewItem = true
    }

    fun getNewItems(visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int) {
        if (Util.canPagingAvailable(
                isCanLoadNewItem,
                visibleItemCount,
                firstVisibleItemPosition,
                totalItemCount
            )
        ) {
            isCanLoadNewItem = false
            getPopularList()
        }
    }
}