package com.jesusrojo.lifecycledemo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.jesusrojo.lifecycledemo.utils.DebugHelp

class MainViewModel : ViewModel() {

    private val myTag = javaClass.simpleName

    init {
        DebugHelp.l(myTag, "init")
    }
}