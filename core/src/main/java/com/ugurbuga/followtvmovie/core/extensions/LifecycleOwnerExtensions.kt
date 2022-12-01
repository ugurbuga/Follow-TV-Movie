package com.ugurbuga.followtvmovie.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

fun <T> LifecycleOwner.collect(stateFlow: StateFlow<T>, observer: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        stateFlow.collect { t -> observer(t) }
    }
}

fun <T> LifecycleOwner.collect(sharedFlow: SharedFlow<T>, observer: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        sharedFlow.collect { t -> observer(t) }
    }
}