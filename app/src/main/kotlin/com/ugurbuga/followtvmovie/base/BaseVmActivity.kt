package com.ugurbuga.followtvmovie.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

abstract class BaseVmActivity<VM : BaseViewModel> : BaseActivity() {

    protected lateinit var mViewModel: VM

    abstract fun getViewModel(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this)[getViewModel()]

    }

}