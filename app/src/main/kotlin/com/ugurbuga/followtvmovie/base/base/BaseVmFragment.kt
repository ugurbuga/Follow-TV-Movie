package com.ugurbuga.followtvmovie.base.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel

abstract class BaseVmFragment<VM : FTMBaseViewModel> : BaseFragment() {

    protected lateinit var viewModel: VM

    abstract fun generateViewModel(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[generateViewModel()]
    }
}