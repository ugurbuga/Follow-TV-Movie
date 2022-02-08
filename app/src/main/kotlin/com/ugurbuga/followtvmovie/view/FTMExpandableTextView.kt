package com.ugurbuga.followtvmovie.view

import android.content.Context
import android.util.AttributeSet
import com.ugurbuga.followtvmovie.R

class FTMExpandableTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = R.attr.ftmTextView
) : FTMTextView(
    context, attributeSet, defStyleAttr
) {

    init {
        isClickable = false

        setOnClickListener {
            if (lineCount > MAX_LINE_COUNT) {
                maxLines = MAX_LINE_COUNT
                setDownArrow()
            } else {
                maxLines = Int.MAX_VALUE
                setUpArrow()
            }
        }
        post {
            updateLineCount()
        }
    }

    private fun updateLineCount() {
        if (!text.isNullOrBlank() && lineCount > 0) {
            if (lineCount > MAX_LINE_COUNT) {
                isClickable = true
                maxLines = 3
                setDownArrow()
            } else {
                isClickable = false
                removeArrow()
            }
        }
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        updateLineCount()
    }

    private fun setDownArrow() {
        setEndDrawable(R.drawable.ic_arrow_down)

    }

    private fun setUpArrow() {
        setEndDrawable(R.drawable.ic_arrow_up)
    }

    private fun removeArrow() {
        setEndDrawable(0)
    }

    private fun setEndDrawable(arrow: Int) {
        setCompoundDrawablesRelativeWithIntrinsicBounds(
            0,
            0,
            arrow,
            0
        )
    }

    companion object {
        const val MAX_LINE_COUNT = 3
    }

}
