package com.ugurbuga.followtvmovie.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel

abstract class BaseVmDbFragment<VM : FTMBaseViewModel, DB : ViewDataBinding> :
    BaseVMFragment<VM>() {

    protected lateinit var viewBinding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, getResourceLayoutId(), container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitDataBinding()
    }

    abstract fun onInitDataBinding()

}