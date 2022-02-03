package com.ugurbuga.followtvmovie.base.base

import androidx.lifecycle.ViewModelProvider
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.extensions.lazyThreadSafetyNone
import java.lang.reflect.ParameterizedType

abstract class BaseVMFragment<VM : FTMBaseViewModel> : BaseFragment() {

    @Suppress("UNCHECKED_CAST")
    val viewModel by lazyThreadSafetyNone {
        val persistentViewModelClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VM>
        return@lazyThreadSafetyNone ViewModelProvider(this)[persistentViewModelClass]
    }

}