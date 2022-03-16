package com.ugurbuga.followtvmovie.ui.search

import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentSearchBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.extensions.scrollEndListener
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import com.ugurbuga.followtvmovie.ui.discover.adapter.PosterHolderType
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : FTMBaseVMFragment<SearchViewModel, FragmentSearchBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_search

    override fun generateViewModel() = SearchViewModel::class.java

    override fun onInitDataBinding() {
        collect(viewModel.searchViewState, ::onSearchViewState)
        with(viewBinding) {
            val favoriteAdapter = FavoriteAdapter(requireContext(), ::onPosterItemClick)
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (favoriteAdapter.getItemViewType(position)) {
                        PosterHolderType.POSTER -> 1
                        PosterHolderType.EMPTY -> 2
                        PosterHolderType.LOADING -> 2
                        else -> 2
                    }
                }
            }
            searchListRecyclerView.apply {
                layoutManager = gridLayoutManager
                adapter = favoriteAdapter
                scrollEndListener {
                    viewModel.getNewItems()
                }
            }
            toolbar.setSearchView(
                menuSearchItemId = R.id.search,
                isExpand = true,
                onQueryChanged = ::onQueryChanged
            )

            toolbar.setNavigationClickListener {
                popBack()
            }
        }
    }

    private fun onQueryChanged(query: String) {
        viewModel.onTextChanged(query)
    }

    private fun onPosterItemClick(
        poster: PosterItemUIModel, imageView: AppCompatImageView
    ) {
        when (poster.mediaType) {
            MediaType.MOVIE -> {
                val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
                val directions =
                    SearchFragmentDirections.actionSearchToMovieDetail(poster.id, poster.posterPath)
                navigate(directions, extras)
            }
            MediaType.TV -> {
                showErrorDialog(R.string.coming_soon, 0)
            }
            MediaType.PERSON -> {
                val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_big))
                val directions = SearchFragmentDirections.actionSearchToPersonDetail(
                    poster.id, poster.posterPath
                )
                navigate(directions, extras)

            }
        }
    }

    private fun onSearchViewState(searchViewState: SearchViewState) {
        viewBinding.viewState = searchViewState
    }

}