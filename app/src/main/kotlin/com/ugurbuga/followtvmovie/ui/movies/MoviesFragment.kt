package com.ugurbuga.followtvmovie.ui.movies

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentMoviesBinding
import com.ugurbuga.followtvmovie.ui.favorite.FavoriteFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : FTMBaseVMFragment<MoviesViewModel, FragmentMoviesBinding>() {

    private val sharedViewModel : MoviesViewModel by activityViewModels()

    override fun getResourceLayoutId() = R.layout.fragment_movies

    override fun generateViewModel() = MoviesViewModel::class.java

    private val adapter: FavoriteFragmentAdapter by lazy {
        FavoriteFragmentAdapter(
            requireContext(),
            childFragmentManager
        )
    }


    override fun onInitDataBinding() {
        with(viewBinding) {
            setToolbar()
            viewPager.adapter = adapter
        }
    }

    private fun setToolbar() {
        val menuItem = viewBinding.toolbar.menu.findItem(R.id.search)

        val searchView = menuItem.actionView as SearchView
        val searchIcon: ImageView =
            searchView.findViewById(androidx.appcompat.R.id.search_button)

        searchIcon.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_search
            )
        )

        val backgroundView = searchView.findViewById(androidx.appcompat.R.id.search_plate) as View
        backgroundView.background = null

        val searchAutoComplete: SearchView.SearchAutoComplete =
            searchView.findViewById(androidx.appcompat.R.id.search_src_text)

        searchAutoComplete.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.primary_color
            )
        )
        searchAutoComplete.typeface =
            ResourcesCompat.getFont(requireContext(), R.font.league_spartan_regular)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String): Boolean {
                viewModel.setQuery(text)
                return false
            }

            override fun onQueryTextChange(text: String): Boolean {
                viewModel.setQuery(text)
                return false
            }
        })
    }
}