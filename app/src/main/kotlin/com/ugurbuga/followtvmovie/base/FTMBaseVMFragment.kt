package com.ugurbuga.followtvmovie.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.ugurbuga.followtvmovie.base.base.BaseVmDbFragment
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.extensions.getString
import com.ugurbuga.followtvmovie.extensions.observeEvent
import com.ugurbuga.followtvmovie.view.dialog.FTMDialog
import com.ugurbuga.followtvmovie.view.loading.FTMLoadingDialog
import com.ugurbuga.followtvmovie.view.toolbar.ToolbarViewState

abstract class FTMBaseVMFragment<VM : FTMBaseViewModel, DB : ViewDataBinding> :
    BaseVmDbFragment<VM, DB>(), FTMBaseView {

    private val loading: FTMLoadingDialog by lazy { FTMLoadingDialog(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        observeEvent(mViewModel.baseEvent, ::onViewEvent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        setToolbarProperties()
    }

    fun setToolbarProperties() {
        when (val toolbarState = getToolbarViewState()) {
            is ToolbarViewState.NoToolbar -> {
            }
            is ToolbarViewState.ToolbarProperties -> {
                if (requireActivity() is FTMBaseVmDbActivity<*, *>) {
                    (requireActivity() as FTMBaseVmDbActivity<*, *>).setToolbarValue(
                        toolbarState.navigationIconType,
                        toolbarState.title
                    )
                }
            }
        }
    }

    override fun onViewEvent(baseViewEvent: FTMBaseViewEvent) {
        when (baseViewEvent) {

            is FTMBaseViewEvent.ShowErrorMessage -> {
                showErrorDialog(baseViewEvent.message, baseViewEvent.errorId)
            }

            FTMBaseViewEvent.DismissLoading -> {
                dismissLoading()
            }

            FTMBaseViewEvent.ShowLoading -> {
                showLoading()
            }
        }
    }

    override fun showErrorDialog(message: Any, errorId: Int?) {
        FTMDialog(requireContext())
            .setTitle(getString(R.string.warning))
            .setMessage(message.getString(requireContext()))
            .setPositiveButton(getString(R.string.ok)) { it, _ ->
                it.dismiss()
            }.show()
    }

    override fun showLoading() {
        if (!loading.isShowing) {
            loading.show()
        }
    }

    override fun dismissLoading() {
        loading.dismiss()
    }

    abstract fun getToolbarViewState(): ToolbarViewState

    fun showDialog(dialog: DialogFragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val ft = fragmentManager.beginTransaction()

        ft.addToBackStack(null)

        dialog.show(ft, this.javaClass.simpleName)
    }

    protected fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections.actionId, navDirections.arguments)
    }

    fun popBack(@IdRes destinationId: Int, inclusive: Boolean = false) {
        findNavController().popBackStack(destinationId, inclusive)
    }

    fun popBack(): Boolean {
        return findNavController().popBackStack()
    }
}
