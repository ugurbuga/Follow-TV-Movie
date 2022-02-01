package com.ugurbuga.followtvmovie.base

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.akexorcist.localizationactivity.core.LanguageSetting
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.akexorcist.localizationactivity.core.OnLocaleChangedListener
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.base.BaseVmDbActivity
import com.ugurbuga.followtvmovie.common.ViewBindingUtil
import com.ugurbuga.followtvmovie.databinding.ActivityFtmBaseDefaultBinding
import com.ugurbuga.followtvmovie.databinding.ActivityFtmBaseFullscreenBinding
import com.ugurbuga.followtvmovie.extensions.getString
import com.ugurbuga.followtvmovie.extensions.observeEvent
import com.ugurbuga.followtvmovie.view.dialog.FTMDialog
import com.ugurbuga.followtvmovie.view.loading.FTMLoadingDialog
import com.ugurbuga.followtvmovie.view.toolbar.FTMToolbar
import com.ugurbuga.followtvmovie.view.toolbar.FTMToolbarType
import java.util.*

abstract class FTMBaseVmDbActivity<VM : FTMBaseViewModel, DB : ViewDataBinding> :
    BaseVmDbActivity<VM, DB>(), FTMBaseView, OnLocaleChangedListener {

    private lateinit var baseViewBinding: ViewDataBinding

    private val loading: FTMLoadingDialog by lazy { FTMLoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        localizationDelegate.addOnLocaleChangedListener(this)
        localizationDelegate.onCreate()
        super.onCreate(savedInstanceState)
        initToolbar()
        observeEvent(mViewModel.baseEvent, ::onViewEvent)
    }

    override fun setResourceViewBinding(): View {
        when (getToolbarType()) {
            is FTMToolbarType.NoToolbar -> {
                baseViewBinding =
                    ViewBindingUtil.inflate<ActivityFtmBaseFullscreenBinding>(layoutInflater)
                mViewBinding = ViewBindingUtil.inflate(
                    layoutInflater,
                    (baseViewBinding as ActivityFtmBaseFullscreenBinding).baseContentFrame,
                    true,
                    getViewBinding()
                )
                return baseViewBinding.root
            }
            is FTMToolbarType.BaseToolbar -> {
                baseViewBinding =
                    ViewBindingUtil.inflate<ActivityFtmBaseDefaultBinding>(layoutInflater)
                mViewBinding = ViewBindingUtil.inflate(
                    layoutInflater,
                    (baseViewBinding as ActivityFtmBaseDefaultBinding).baseContentFrame,
                    true,
                    getViewBinding()

                )
                return baseViewBinding.root
            }
        }
    }

    override fun onViewEvent(baseViewEvent: FTMBaseViewEvent) {
        when (baseViewEvent) {

            is FTMBaseViewEvent.ShowErrorMessage -> {
                showErrorDialog(baseViewEvent.message, baseViewEvent.errorId)
            }

            FTMBaseViewEvent.DismissLoading -> {
                dismissLoading()
            }

            FTMBaseViewEvent.ShowLoading -> {
                showLoading()
            }
        }
    }

    abstract fun getToolbarType(): FTMToolbarType

    override fun showErrorDialog(message: Any, errorId: Int?) {
        FTMDialog(this)
            .setTitle(getString(R.string.warning))
            .setMessage(message.getString(this))
            .setPositiveButton(getString(R.string.ok)) { it, _ ->
                it.dismiss()
            }.show()
    }

    override fun showLoading() {
        if (!loading.isShowing) {
            loading.show()
        }
    }

    override fun dismissLoading() {
        loading.dismiss()
    }

    private fun initToolbar() {
        if (baseViewBinding is ActivityFtmBaseDefaultBinding) {
            val toolbar = (baseViewBinding as ActivityFtmBaseDefaultBinding).baseContentToolbar
            setSupportActionBar(toolbar)
            toolbar.setNavigationClickListener(object :
                FTMToolbar.NavigationClickListener {
                override fun onNavigationClickListener() {
                    onBackPressed()
                }
            })
        }
    }

    open fun setToolbarValue(
        navigationIconType: FTMToolbar.NavigationIconType,
        title: String
    ) {
        if (baseViewBinding is ActivityFtmBaseDefaultBinding) {
            (baseViewBinding as ActivityFtmBaseDefaultBinding).baseContentToolbar.apply {
                this.navigationIconType = navigationIconType
                this.title = title
            }
        }
    }

    private val localizationDelegate = LocalizationActivityDelegate(this)

    public override fun onResume() {
        super.onResume()
        localizationDelegate.onResume(this)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun attachBaseContext(newBase: Context) {
        applyOverrideConfiguration(localizationDelegate.updateConfigurationLocale(newBase))
        super.attachBaseContext(newBase)
    }

    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }

    override fun getResources(): Resources {
        return localizationDelegate.getResources(super.getResources())
    }

    fun setLanguage(newLanguage: String) {
        LanguageSetting.setLanguage(this, Locale(newLanguage))
    }

    val currentLanguage: Locale
        get() = localizationDelegate.getLanguage(this)

    // Just override method locale change event
    override fun onBeforeLocaleChanged() {}
    override fun onAfterLocaleChanged() {}

}
