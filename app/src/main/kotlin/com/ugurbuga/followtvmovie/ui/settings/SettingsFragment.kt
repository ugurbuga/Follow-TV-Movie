package com.ugurbuga.followtvmovie.ui.settings

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.data.preferences.FTMPreferenceManager
import com.ugurbuga.followtvmovie.databinding.FragmentSettingsBinding
import com.ugurbuga.followtvmovie.ui.main.MainActivity
import com.ugurbuga.followtvmovie.view.dialog.FTMDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : FTMBaseVMFragment<SettingsViewModel, FragmentSettingsBinding>() {

    @Inject
    lateinit var preferenceManager: FTMPreferenceManager


    override fun getResourceLayoutId() = R.layout.fragment_settings

    private var selectedIndex: Int = -1

    private lateinit var themeOptions: Array<String>

    override fun onInitDataBinding() {
        themeOptions = arrayOf(
            getString(R.string.light_theme),
            getString(R.string.dark_theme),
            getString(R.string.system_default)
        )
        selectedIndex = getIndexFromTheme(preferenceManager.getTheme())

        with(viewBinding) {
            themeLayout.setOnClickListener {
                showThemeDialog()
            }
            currentTheme.text = themeOptions[selectedIndex]

            toolbar.setNavigationClickListener {
                popBack()
            }
        }
    }

    private fun showThemeDialog() {
        val builder = FTMDialog(requireContext())
        builder.setTitle(getString(R.string.change_theme))

        builder.setSingleChoiceItems(themeOptions, selectedIndex) { dialog, which ->
            if (which != selectedIndex) {
                dialog.dismiss()
                selectedIndex = which
                onThemeClicked()
            }
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun onThemeClicked() {
        viewBinding.currentTheme.text = themeOptions[selectedIndex]
        updateTheme()
    }

    private fun getIndexFromTheme(theme: Int): Int {
        return when (theme) {
            AppCompatDelegate.MODE_NIGHT_NO -> 0
            AppCompatDelegate.MODE_NIGHT_YES -> 1
            else -> 2
        }
    }

    private fun getThemeFromIndex(): Int {
        return when (selectedIndex) {
            0 -> AppCompatDelegate.MODE_NIGHT_NO
            1 -> AppCompatDelegate.MODE_NIGHT_YES
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                } else {
                    AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                }
            }
        }
    }

    private fun updateTheme() {
        setTheme(getThemeFromIndex())
        AppCompatDelegate.setDefaultNightMode(getThemeFromIndex())
    }

    private fun setTheme(theme: Int) {
        preferenceManager.setTheme(theme)
        (requireActivity() as? MainActivity)?.setAppTheme(theme)
    }
}