package com.ugurbuga.followtvmovie.core.base

import androidx.lifecycle.ViewModelProvider
import com.ugurbuga.followtvmovie.core.extensions.lazyThreadSafetyNone
import java.lang.reflect.ParameterizedType

abstract class BaseVmActivity<VM : BaseViewModel> : BaseActivity() {

    @Suppress("UNCHECKED_CAST")
    protected val viewModel by lazyThreadSafetyNone {
        val persistentViewModelClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VM>
        return@lazyThreadSafetyNone ViewModelProvider(this)[persistentViewModelClass]
    }

}