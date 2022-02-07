package com.ugurbuga.followtvmovie.ui.moviedetail

sealed class MovieDetailViewEvent {

    object ShowDeletedSnackbar : MovieDetailViewEvent()

    object ShowAddedSnackbar : MovieDetailViewEvent()

}
