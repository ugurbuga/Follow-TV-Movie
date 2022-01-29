package com.ugurbuga.followtvmovie.view.loading

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.Util
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

    fun show(title: Any? = null, subTitle: Any? = null, isTransparent: Boolean = false) {
        setLoadingText(title, subTitle)
        if (!isTransparent) {
            binding.mainLayout.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.primary_color_95
                )
            )
        } else {
            binding.mainLayout.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.transparent
                )
            )
        }

        super.show()
    }

    private fun setLoadingText(title: Any? = null, subTitle: Any? = null) {
        var mTitle = Util.EMPTY_STRING
        var mSubTitle = Util.EMPTY_STRING
        if (title is String) {
            mTitle = title
        }
        if (subTitle is String) {
            mSubTitle = subTitle
        }
        if (title is Int) {
            mTitle = context.getString(title)
        }
        if (subTitle is Int) {
            mSubTitle = context.getString(subTitle)
        }

        title?.let {
            binding.loadingTitleTextView.text = mTitle
            binding.loadingTitleTextView.visibility = View.VISIBLE
        } ?: run {
            binding.loadingTitleTextView.text = Util.EMPTY_STRING
            binding.loadingTitleTextView.visibility = View.GONE
        }

        subTitle?.let {
            binding.loadingSubTitleTextView.text = mSubTitle
            binding.loadingSubTitleTextView.visibility = View.VISIBLE
        } ?: run {
            binding.loadingSubTitleTextView.text = Util.EMPTY_STRING
            binding.loadingSubTitleTextView.visibility = View.GONE
        }
    }
}
