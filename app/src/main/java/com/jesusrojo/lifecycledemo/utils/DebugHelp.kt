package com.jesusrojo.lifecycledemo.utils

import timber.log.Timber

// _up DEBUG
class DebugHelp {

    companion object{

        private const val tagJR = "##"

        var fullLog: String = ""

        private fun l(message: String) {
            fullLog += "\n$message"
            Timber.d("$message $tagJR")
        }

        fun l(className: String, message: String) {
            l("$className $message $tagJR")
        }

        fun lt(message: String) {
            l("$message THREAD: ${Thread.currentThread().name} $tagJR")
        }

    }
}