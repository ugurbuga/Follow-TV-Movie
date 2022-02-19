package com.ugurbuga.followtvmovie.ui.discover

import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.popular.movie.usecase.PopularMovieUseCase
import com.ugurbuga.followtvmovie.domain.popular.movie.usecase.UpcomingMovieUseCase
import com.ugurbuga.followtvmovie.domain.popular.tvshow.usecase.PopularTvShowUseCase
import com.ugurbuga.followtvmovie.domain.poster.model.LoadingUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val popularMovieUseCase: PopularMovieUseCase,
    private val upcomingMovieUseCase: UpcomingMovieUseCase,
    private val popularTvShowUseCase: PopularTvShowUseCase,
) : FTMBaseViewModel() {

    private val _popularMovies = MutableStateFlow(mutableListOf<ListAdapterItem>())
    val popularMovies: StateFlow<MutableList<ListAdapterItem>> get() = _popularMovies

    private val _upcomingMovies = MutableStateFlow(mutableListOf<ListAdapterItem>())
    val upcomingMovies: StateFlow<MutableList<ListAdapterItem>> get() = _upcomingMovies

    private val _popularTvShows = MutableStateFlow(mutableListOf<ListAdapterItem>())
    val popularTvShows: StateFlow<MutableList<ListAdapterItem>> get() = _popularTvShows

    var upPopularMoviePage = 0
    var popularTvShowPage = 0
    var upComingMoviePage = 0

    var isCanLoadNewItemPopularMovie = false
    var isCanLoadNewItemPopularTvShow = false
    var isCanLoadNewItemUpcomingMovie = false

    init {
        getPopularMovies()
        getUpcomingMovies()
        getPopularTvShows()
    }

    // Begin --> Popular Movie
    private fun getPopularMovies() {
        this.upPopularMoviePage++
        addLoadingPopularMovie()
        popularMovieUseCase(PopularMovieUseCase.PopularMovieParams(this.upPopularMoviePage)).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            setPopularMovieList(it)
        }.launchIn(viewModelScope)
    }

    private fun addLoadingPopularMovie() {
        val oldList = popularMovies.value.toMutableList()
        oldList.add(LoadingUIModel())
        _popularMovies.value = oldList
    }

    private fun setPopularMovieList(posterModel: PosterUIModel) {
        val oldList = popularMovies.value.toMutableList()
        oldList.remove(LoadingUIModel())
        oldList.addAll(posterModel.posterList)
        _popularMovies.value = oldList
        isCanLoadNewItemPopularMovie = posterModel.totalPages > posterModel.page
    }

    fun getNewItemsPopularMovie(
        visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int
    ) {
        if (Util.canPagingAvailable(
                isCanLoadNewItemPopularMovie,
                visibleItemCount,
                firstVisibleItemPosition,
                totalItemCount
            )
        ) {
            isCanLoadNewItemPopularMovie = false
            getPopularMovies()
        }
    }

    // End --> Popular Movie

    // Begin --> Upcoming Movie
    private fun getUpcomingMovies() {
        this.upComingMoviePage++
        addLoadingUpcomingMovie()
        upcomingMovieUseCase(UpcomingMovieUseCase.UpcomingMovieParams(this.upComingMoviePage)).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            setUpcomingMovieList(it)
        }.launchIn(viewModelScope)
    }

    private fun addLoadingUpcomingMovie() {
        val oldList = upcomingMovies.value
        oldList.add(LoadingUIModel())
        _upcomingMovies.value = oldList
    }

    private fun setUpcomingMovieList(posterModel: PosterUIModel) {
        val oldList = upcomingMovies.value
        oldList.remove(LoadingUIModel())
        oldList.addAll(posterModel.posterList)
        _upcomingMovies.value = oldList
        isCanLoadNewItemUpcomingMovie = posterModel.totalPages > posterModel.page
    }

    fun getNewItemsUpcomingMovie(
        visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int
    ) {
        if (Util.canPagingAvailable(
                isCanLoadNewItemUpcomingMovie,
                visibleItemCount,
                firstVisibleItemPosition,
                totalItemCount
            )
        ) {
            isCanLoadNewItemUpcomingMovie = false
            getUpcomingMovies()
        }
    }
    // End --> Upcoming Movie

    // Begin --> Popular Tv Show

    private fun getPopularTvShows() {
        this.popularTvShowPage++
        addLoadingPopularTvShow()
        popularTvShowUseCase(PopularTvShowUseCase.PopularTvShowParams(popularTvShowPage)).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            setPopularTvShowList(it)
        }.launchIn(viewModelScope)
    }

    private fun addLoadingPopularTvShow() {
        val oldList = popularTvShows.value
        oldList.add(LoadingUIModel())
        _popularTvShows.value = oldList
    }

    private fun setPopularTvShowList(posterModel: PosterUIModel) {
        val oldList = popularTvShows.value
        oldList.remove(LoadingUIModel())
        oldList.addAll(posterModel.posterList)
        _popularTvShows.value = oldList
        isCanLoadNewItemPopularTvShow = posterModel.totalPages > posterModel.page
    }

    fun getNewItemsPopularTvShow(
        visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int
    ) {
        if (Util.canPagingAvailable(
                isCanLoadNewItemPopularTvShow,
                visibleItemCount,
                firstVisibleItemPosition,
                totalItemCount
            )
        ) {
            isCanLoadNewItemPopularTvShow = false
            getPopularTvShows()
        }
    }

}