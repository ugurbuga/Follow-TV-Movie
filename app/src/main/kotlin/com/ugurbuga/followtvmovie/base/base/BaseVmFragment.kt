package com.ugurbuga.followtvmovie.base.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel

abstract class BaseVMFragment<VM : FTMBaseViewModel> : BaseFragment() {

    protected lateinit var mViewModel: VM

    abstract fun getViewModel(): Class<VM>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = createViewModel()
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this)[getViewModel()]
    }
}