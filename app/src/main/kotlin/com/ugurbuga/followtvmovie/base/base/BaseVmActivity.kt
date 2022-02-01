package com.ugurbuga.followtvmovie.base.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel

abstract class BaseVmActivity<VM : FTMBaseViewModel> : BaseActivity() {

    protected lateinit var mViewModel: VM

    abstract fun getViewModel(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this)[getViewModel()]

    }

}