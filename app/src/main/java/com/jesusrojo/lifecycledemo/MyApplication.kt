package com.jesusrojo.lifecycledemo

import android.app.Application
import android.content.res.Configuration
import com.jesusrojo.lifecycledemo.utils.DebugHelp
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import timber.log.Timber

class MyApplication : Application() {

    private val myTag = javaClass.simpleName
    private fun l(message: String) = DebugHelp.l(myTag, message)
    private var mRefWatcher: RefWatcher? = null

    override fun onCreate() {
        super.onCreate()
         l("onCreate")
        if (BuildConfig.DEBUG) {
            setupTimber()
            setupLeakCanary()
        }
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

    //LEAK CANARY
    private fun setupLeakCanary() {
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            mRefWatcher = LeakCanary.install(this)
        }
    }

    fun mustDieLeakCanary(`object`: Any?) {
        mRefWatcher?.watch(`object`)
    }

    // APP
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        l("onConfigurationChanged")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        l("onLowMemory")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        l("onTrimMemory")
    }

    override fun onTerminate() {
        super.onTerminate()
        l("onTerminate")
    }
}