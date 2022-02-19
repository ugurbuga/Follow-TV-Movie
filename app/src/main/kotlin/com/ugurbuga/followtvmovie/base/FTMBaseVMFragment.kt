package com.ugurbuga.followtvmovie.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.ugurbuga.followtvmovie.base.base.BaseVmDbFragment
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.view.loading.FTMLoadingDialog

abstract class FTMBaseVMFragment<VM : FTMBaseViewModel, DB : ViewDataBinding> :
    BaseVmDbFragment<VM, DB>() {

    private val loading: FTMLoadingDialog by lazy { FTMLoadingDialog(requireContext()) }

    private var baseView: FTMBaseView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FTMBaseView) {
            baseView = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        collect(viewModel.baseEvent, ::onViewEvent)
    }

    private fun onViewEvent(baseViewEvent: FTMBaseViewEvent) {
        baseView?.onViewEvent(baseViewEvent)
    }

    fun showErrorDialog(message: Any, errorId: Int?) {
        baseView?.showErrorDialog(message, errorId)
    }

    fun showLoading() {
        baseView?.showLoading()
    }

    fun dismissLoading() {
        baseView?.dismissLoading()
    }

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
