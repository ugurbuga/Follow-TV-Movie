package com.ugurbuga.followtvmovie.base

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.akexorcist.localizationactivity.core.LanguageSetting
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.akexorcist.localizationactivity.core.OnLocaleChangedListener
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.base.BaseVmDbActivity
import com.ugurbuga.followtvmovie.extensions.getString
import com.ugurbuga.followtvmovie.extensions.observeEvent
import com.ugurbuga.followtvmovie.view.dialog.FTMDialog
import com.ugurbuga.followtvmovie.view.loading.FTMLoadingDialog
import java.util.*

abstract class FTMBaseVmDbActivity<VM : FTMBaseViewModel, DB : ViewDataBinding> :
    BaseVmDbActivity<VM,DB>(), FTMBaseView, OnLocaleChangedListener {

    private val loading: FTMLoadingDialog by lazy { FTMLoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        localizationDelegate.addOnLocaleChangedListener(this)
        localizationDelegate.onCreate()
        super.onCreate(savedInstanceState)
        observeEvent(viewModel.baseEvent, ::onViewEvent)
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

    private val localizationDelegate = LocalizationActivityDelegate(this)

    public override fun onResume() {
        super.onResume()
        localizationDelegate.onResume(this)
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
