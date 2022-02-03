package com.ugurbuga.followtvmovie.base.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel


abstract class BaseVmDbActivity<VM : FTMBaseViewModel, DB : ViewDataBinding> :
    BaseVmActivity<VM>() {

    protected lateinit var viewBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, getLayoutResourceId())
        viewBinding.lifecycleOwner = this
        onInitDataBinding()
    }

    @LayoutRes
    abstract fun getLayoutResourceId(): Int

    abstract fun onInitDataBinding()

}