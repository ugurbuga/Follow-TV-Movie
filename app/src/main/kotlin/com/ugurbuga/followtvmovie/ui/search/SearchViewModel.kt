package com.ugurbuga.followtvmovie.ui.search

import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.poster.model.EmptyUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.LoadingUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.domain.search.GetSearchUseCase
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
) : FTMBaseViewModel() {

    private var job: Job? = null
    private val _searchViewState = MutableStateFlow(SearchViewState())
    val searchViewState: StateFlow<SearchViewState>
        get() = _searchViewState

    var isCanLoadNewItem = false
    var currentPage = 1
    private var currentText = Util.EMPTY_STRING

    fun onTextChanged(text: CharSequence?) {
        currentPage = 1
        _searchViewState.value = SearchViewState()
        if (!text.isNullOrBlank()) {
            currentText = text.toString()
            getSearch()
        }
    }

    private fun getSearch() {
        isCanLoadNewItem = false
        job?.cancel()
        job = getSearchUseCase(
            GetSearchUseCase.SearchParams(
                currentText, currentPage
            )
        ).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            if (it.posterList.isEmpty()) {
                _searchViewState.value =
                    SearchViewState(PosterUIModel(arrayListOf(EmptyUIModel(message = R.string.item_not_found))))

            } else {
                val temp = searchViewState.value.poster.posterList.toMutableList()
                temp.addAll(it.posterList)
                temp.remove(LoadingUIModel())
                temp.remove(EmptyUIModel(message = R.string.item_not_found))
                it.posterList = temp

                _searchViewState.value = SearchViewState(it)
            }
            currentPage = it.page
            isCanLoadNewItem = it.totalPages > it.page

        }.launchIn(viewModelScope)
    }

    fun getNewItems() {
        if (isCanLoadNewItem) {
            isCanLoadNewItem = false
            currentPage++
            val temp = searchViewState.value.poster.posterList.toMutableList()
            temp.add(LoadingUIModel())
            _searchViewState.value = searchViewState.value.copy(
                poster = PosterUIModel(posterList = temp)
            )
            getSearch()
        }
    }
}