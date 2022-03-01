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
import com.ugurbuga.followtvmovie.ui.discover.adapter.PosterViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val popularMovieUseCase: PopularMovieUseCase,
    private val upcomingMovieUseCase: UpcomingMovieUseCase,
    private val popularTvShowUseCase: PopularTvShowUseCase,
) : FTMBaseViewModel() {

    private val _discover = MutableStateFlow(PosterViewState())
    val discover: StateFlow<PosterViewState> get() = _discover

    private var upPopularMoviePage = 0
    private var popularTvShowPage = 0
    private var upComingMoviePage = 0

    private var isCanLoadNewItemPopularMovie = false
    private var isCanLoadNewItemPopularTvShow = false
    private var isCanLoadNewItemUpcomingMovie = false

    init {
        getPopularMovies()
        getUpcomingMovies()
        getPopularTvShows()
    }

    // Begin --> Popular Movie
    private fun getPopularMovies() {
        this.upPopularMoviePage++
        addLoadingPopularMovie()
        popularMovieUseCase(PopularMovieUseCase.PopularMovieParams(this.upPopularMoviePage))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                setPopularMovieList(it)
            }.launchIn(viewModelScope)
    }

    private fun addLoadingPopularMovie() {
        val oldList = getOldListPopularMovie()
        oldList.add(LoadingUIModel())
        _discover.value = updatePopularMovieList(oldList)
    }

    private fun updatePopularMovieList(oldList: MutableList<ListAdapterItem>): PosterViewState {
        return discover.value.copy(popularMovieList = oldList)
    }

    private fun getOldListPopularMovie(): MutableList<ListAdapterItem> {
        return discover.value.popularMovieList.toMutableList()
    }

    private fun setPopularMovieList(posterModel: PosterUIModel) {
        val oldList = getOldListPopularMovie()
        oldList.remove(LoadingUIModel())
        oldList.addAll(posterModel.posterList)
        _discover.value = updatePopularMovieList(oldList)
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
        val oldList = getOldListUpcomingMovie()
        oldList.add(LoadingUIModel())
        _discover.value = updateUpcomingMovieList(oldList)
    }

    private fun updateUpcomingMovieList(oldList: MutableList<ListAdapterItem>): PosterViewState {
        return discover.value.copy(upcomingMovieList = oldList)
    }

    private fun getOldListUpcomingMovie(): MutableList<ListAdapterItem> {
        return discover.value.upcomingMovieList.toMutableList()
    }

    private fun setUpcomingMovieList(posterModel: PosterUIModel) {
        val oldList = getOldListUpcomingMovie()
        oldList.remove(LoadingUIModel())
        oldList.addAll(posterModel.posterList)
        _discover.value = updateUpcomingMovieList(oldList)
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
        val oldList = getOldListPopularTvShow()
        oldList.add(LoadingUIModel())
        _discover.value = updatePopularTvShowList(oldList)
    }

    private fun updatePopularTvShowList(oldList: MutableList<ListAdapterItem>): PosterViewState {
        return discover.value.copy(popularTvShowList = oldList)
    }

    private fun getOldListPopularTvShow(): MutableList<ListAdapterItem> {
        return discover.value.popularTvShowList.toMutableList()
    }

    private fun setPopularTvShowList(posterModel: PosterUIModel) {
        val oldList = getOldListPopularTvShow()
        oldList.remove(LoadingUIModel())
        oldList.addAll(posterModel.posterList)
        _discover.value = updatePopularTvShowList(oldList)
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