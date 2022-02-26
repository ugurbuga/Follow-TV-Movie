package com.ugurbuga.followtvmovie.ui.search

import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.domain.poster.model.EmptyUIModel
import com.ugurbuga.followtvmovie.domain.search.GetSearchUseCase
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
) : FTMBaseViewModel() {

    private val _searchViewState = MutableStateFlow(SearchViewState())
    val searchViewState: StateFlow<SearchViewState>
        get() = _searchViewState

    fun onTextChanged(text: CharSequence?) {
        if (!text.isNullOrBlank()) {
            getSearch(text.toString())
        } else {
            _searchViewState.value = SearchViewState(arrayListOf())
        }
    }

    private fun getSearch(text: String) {
        getSearchUseCase(GetSearchUseCase.SearchParams(text, 1))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                if (it.posterList.isEmpty()) {
                    _searchViewState.value =
                        SearchViewState(arrayListOf(EmptyUIModel(message = R.string.item_not_found)))

                } else {
                    _searchViewState.value =
                        SearchViewState(it.posterList as ArrayList<ListAdapterItem>)
                }

            }.launchIn(viewModelScope)
    }
}