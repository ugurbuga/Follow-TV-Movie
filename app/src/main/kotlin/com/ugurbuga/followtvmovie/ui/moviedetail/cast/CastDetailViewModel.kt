package com.ugurbuga.followtvmovie.ui.moviedetail.cast

import androidx.lifecycle.SavedStateHandle
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CastDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : FTMBaseViewModel() {

    private var movieId: String = savedStateHandle["arg_cast_id"] ?: ""



}