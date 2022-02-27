package com.ugurbuga.followtvmovie.ui.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVmDbActivity
import com.ugurbuga.followtvmovie.data.preferences.FTMPreferenceManager
import com.ugurbuga.followtvmovie.databinding.ActivitySplashBinding
import com.ugurbuga.followtvmovie.extensions.collect
import com.ugurbuga.followtvmovie.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : FTMBaseVmDbActivity<SplashViewModel, ActivitySplashBinding>() {

    override fun getLayoutResourceId() = R.layout.activity_splash

    var loading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        setAppTheme(FTMPreferenceManager(this).getTheme())
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                loading
            }
        }
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onInitDataBinding() {
        collect(viewModel.isLoading, ::onLoading)
    }

    private fun onLoading(loading: Boolean) {
        this.loading = loading
    }

    private fun setAppTheme(theme: Int) {
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