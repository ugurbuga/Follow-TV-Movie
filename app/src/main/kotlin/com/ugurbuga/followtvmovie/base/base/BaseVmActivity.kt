package com.ugurbuga.followtvmovie.base.base

import androidx.lifecycle.ViewModelProvider
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.extensions.lazyThreadSafetyNone
import java.lang.reflect.ParameterizedType

abstract class BaseVmActivity<VM : FTMBaseViewModel> : BaseActivity() {

    @Suppress("UNCHECKED_CAST")
    protected val viewModel by lazyThreadSafetyNone {
        val persistentViewModelClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VM>
        return@lazyThreadSafetyNone ViewModelProvider(this)[persistentViewModelClass]
    }

}