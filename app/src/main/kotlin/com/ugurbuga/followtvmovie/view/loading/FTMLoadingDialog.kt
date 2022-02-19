package com.ugurbuga.followtvmovie.view.loading

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.databinding.LayoutLoadingViewBinding

class FTMLoadingDialog(
    context: Context
) : Dialog(context, R.style.FullScreenDialog) {

    private val binding: LayoutLoadingViewBinding =
        LayoutLoadingViewBinding.inflate(LayoutInflater.from(context))

    init {
        setContentView(binding.root)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}
