package com.ugurbuga.followtvmovie.ui.webview

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.databinding.FragmentWebViewBinding

class WebViewFragment : FTMBaseVMFragment<WebViewViewModel, FragmentWebViewBinding>() {

    val args: WebViewFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
    }

    private fun handleBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (viewBinding.webView.canGoBack()) {
                    viewBinding.webView.goBack()
                } else {
                    isEnabled = false
                    activity?.onBackPressed()
                }
            }
        })
    }

    override fun getResourceLayoutId() = R.layout.fragment_web_view

    override fun onInitDataBinding() {
        initWebView()
    }

    private fun initWebView() {
        viewBinding.webView.apply {
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.displayZoomControls = false
            settings.builtInZoomControls = true
            settings.domStorageEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.javaScriptEnabled = true
            webChromeClient = WebChromeClient()

            webViewClient = object : WebViewClient() {

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    viewBinding.webProgress.isIndeterminate = true
                    viewBinding.webProgress.visibility = View.VISIBLE
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    viewBinding.webProgress.isIndeterminate = false
                    viewBinding.webProgress.visibility = View.GONE
                    super.onPageFinished(view, url)
                }
            }
            loadUrl(args.argUrl)
        }
    }
}
