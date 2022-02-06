package com.ugurbuga.followtvmovie.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.ugurbuga.followtvmovie.common.Event
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import kotlin.reflect.KFunction1

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this) {
        it?.let { t -> observer(t) }
    }
}

fun <T> LifecycleOwner.observeEvent(liveData: LiveData<Event<T>>, observer: (T) -> Unit) {
    liveData.observe(this) {
        it?.getContentIfNotHandled()?.let { t -> observer(t) }
    }
}