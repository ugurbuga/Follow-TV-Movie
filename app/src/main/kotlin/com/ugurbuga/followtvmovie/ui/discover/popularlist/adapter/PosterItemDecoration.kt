package com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.R

class PosterItemDecoration : RecyclerView.ItemDecoration() {

    private val spanCount = 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        if (position == -1) {
            return
        }
        val itemType = parent.adapter?.getItemViewType(position)

        if (itemType == PosterHolderType.POSTER) {

            val column = position % spanCount // item column

            if (column == 0) {
                outRect.left = view.resources.getDimensionPixelSize(R.dimen.padding_16)
                outRect.right = view.resources.getDimensionPixelSize(R.dimen.padding_8)
            } else {
                outRect.left = view.resources.getDimensionPixelSize(R.dimen.padding_8)
                outRect.right = view.resources.getDimensionPixelSize(R.dimen.padding_16)
            }
            outRect.top = view.resources.getDimensionPixelSize(R.dimen.padding_8)
            outRect.bottom = view.resources.getDimensionPixelSize(R.dimen.padding_0)
        }
    }
}
