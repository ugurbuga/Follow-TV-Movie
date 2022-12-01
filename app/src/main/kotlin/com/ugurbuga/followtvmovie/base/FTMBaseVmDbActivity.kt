package com.ugurbuga.followtvmovie.base

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.ViewDataBinding
import com.akexorcist.localizationactivity.core.LanguageSetting
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.akexorcist.localizationactivity.core.OnLocaleChangedListener
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.core.base.BaseVmDbActivity
import com.ugurbuga.followtvmovie.core.extensions.getString
import com.ugurbuga.followtvmovie.di.preferences.FTMPreferenceManager
import com.ugurbuga.followtvmovie.core.extensions.collect
import com.ugurbuga.followtvmovie.view.dialog.FTMDialog
import com.ugurbuga.followtvmovie.view.loading.FTMLoadingDialog
import java.util.Locale

abstract class FTMBaseVmDbActivity<VM : FTMBaseViewModel, DB : ViewDataBinding> :
    BaseVmDbActivity<VM, DB>(), FTMBaseView, OnLocaleChangedListener {

    private val loading: FTMLoadingDialog by lazy { FTMLoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setAppTheme(FTMPreferenceManager(this).getTheme())
        localizationDelegate.addOnLocaleChangedListener(this)
        localizationDelegate.onCreate()
        super.onCreate(savedInstanceState)
        collect(viewModel.baseEvent, ::onViewEvent)
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

    open fun setAppTheme(theme: Int) {
        when (theme) {
            AppCompatDelegate.MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )

            AppCompatDelegate.MODE_NIGHT_NO -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )

            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }

}
