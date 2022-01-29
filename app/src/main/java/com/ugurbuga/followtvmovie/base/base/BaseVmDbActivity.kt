package com.ugurbuga.followtvmovie.base.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.ugurbuga.followtvmovie.common.ViewBindingUtil
import com.ugurbuga.followtvmovie.databinding.ActivityBaseBinding

abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>() {

    protected lateinit var mViewBinding: DB

    abstract fun getViewBinding(): Class<DB>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = setResourceViewBinding()
        setContentView(view)
        mViewBinding.lifecycleOwner = this
        onInitDataBinding()
    }

    // Proje tarafında bir base ihtiyacı olursa method override edilmesi yeterli
    open fun setResourceViewBinding(): View {
        val baseViewBinding = ViewBindingUtil.inflate<ActivityBaseBinding>(layoutInflater)
        mViewBinding = ViewBindingUtil.inflate(
            layoutInflater,
            baseViewBinding.baseNxlContentFrame,
            true,
            getViewBinding()
        )
        return baseViewBinding.root
    }

    abstract fun onInitDataBinding()

}