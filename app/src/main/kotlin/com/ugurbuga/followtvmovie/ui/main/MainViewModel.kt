package com.ugurbuga.followtvmovie.ui.main

import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.BaseViewModel
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.repository.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {

    init {
        getPopularTvShows()
    }

    private fun getPopularTvShows() {
        mainRepository.getPopularTvShows(1)
            .doOnStatusChanged {
                initStatusState(
                    it
                )
            }
            .doOnSuccess {
            }
            .launchIn(viewModelScope)
    }
}