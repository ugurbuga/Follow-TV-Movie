package com.ugurbuga.followtvmovie.core.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Base view holder to standardize and simplify initialization for this component.
 *
 * @param binding View data binding generated class instance.
 * @see RecyclerView.ViewHolder
 */
abstract class BaseViewHolder<T : ViewDataBinding>(
    val binding: T
) : RecyclerView.ViewHolder(binding.root)