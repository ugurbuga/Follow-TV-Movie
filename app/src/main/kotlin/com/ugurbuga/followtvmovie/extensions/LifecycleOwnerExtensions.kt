package com.ugurbuga.followtvmovie.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

fun <T> LifecycleOwner.collect(stateFlow: StateFlow<T>, observer: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        stateFlow.collectLatest { t -> observer(t) }
    }
}

fun <T> LifecycleOwner.collect(sharedFlow: SharedFlow<T>, observer: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        sharedFlow.collectLatest { t -> observer(t) }
    }
}