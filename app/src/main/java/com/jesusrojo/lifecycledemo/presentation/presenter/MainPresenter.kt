@file:Suppress("unused")

package com.jesusrojo.lifecycledemo.presentation.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.jesusrojo.lifecycledemo.utils.DebugHelp

class MainPresenter: LifecycleObserver {

    private val myTag = javaClass.simpleName
    private fun l(message: String) = DebugHelp.l(myTag, message)

//    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
//    fun onAny() { l("onAny") }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create() { l("create") }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() { l("start") }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() { l("resume") }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() { l("pause") }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() { l("stop") }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() { l("destroy") }
}