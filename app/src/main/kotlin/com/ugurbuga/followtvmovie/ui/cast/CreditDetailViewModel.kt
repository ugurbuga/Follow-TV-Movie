package com.ugurbuga.followtvmovie.ui.cast

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.domain.credit.model.GetCreditDetailUseCase
import com.ugurbuga.followtvmovie.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class CreditDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val getCreditDetailUseCase: GetCreditDetailUseCase
) : FTMBaseViewModel() {

    private var creditId: String = savedStateHandle["arg_credit_id"] ?: ""

    private val _creditDetailViewState = MutableStateFlow(CreditDetailViewState())
    val creditDetailViewState: StateFlow<CreditDetailViewState> get() = _creditDetailViewState

    init {
        getCreditDetail()
    }

    private fun getCreditDetail() {
        getCreditDetailUseCase(GetCreditDetailUseCase.CreditDetailParam(creditId)).doOnStatusChanged {
            initStatusState(
                it, isShowLoading = false
            )
        }.doOnSuccess {
            _creditDetailViewState.value = creditDetailViewState.value.copy(creditDetail = it)
        }.launchIn(viewModelScope)
    }

}