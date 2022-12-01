package com.ugurbuga.followtvmovie.ui.moviedetail.review

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseBottomSheetDialogFragment
import com.ugurbuga.followtvmovie.databinding.BottomSheetReviewBinding
import com.ugurbuga.followtvmovie.core.extensions.collect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewBottomSheet : FTMBaseBottomSheetDialogFragment<BottomSheetReviewBinding>() {

    override fun getResourceLayoutId() = R.layout.bottom_sheet_review

    private val viewModel: ReviewViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        collect(viewModel.movieReviewViewState, ::onMovieReviewViewState)
    }

    private fun onMovieReviewViewState(movieReviewViewState: MovieReviewViewState) {
        viewBinding.viewState = movieReviewViewState
    }

    private fun setUpViews() {
        with(viewBinding) {
            reviewRecyclerView.adapter = ReviewAdapter()
        }
    }
}
