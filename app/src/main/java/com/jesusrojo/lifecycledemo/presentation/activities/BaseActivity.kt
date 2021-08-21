package com.jesusrojo.lifecycledemo.presentation.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.jesusrojo.lifecycledemo.MyApplication
import com.jesusrojo.lifecycledemo.utils.DebugHelp

abstract class BaseActivity : AppCompatActivity() {

    private val myTag = javaClass.simpleName

    protected fun l(message: String) = DebugHelp.l(myTag, message)

    protected fun lt(message: String) = DebugHelp.lt(myTag + message)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        l("onCreate BaseActivity###########")
        if (savedInstanceState == null) {
            l("savedInstanceState is null (first time)")
        } else {
            l("savedInstanceState not null (second time)")
        }
    }

    override fun onDestroy() {
        l("onDestroy")
        destroyLeekCanary()
        super.onDestroy()
    }

    // LEAK CANARY
    private fun destroyLeekCanary() {
        val app = application as MyApplication
        app.mustDieLeakCanary(this)
    }

    // TOAST
    protected fun toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


    // LIFECYCLE
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        l("onPostCreate")
    }

    override fun onStart() {
        super.onStart()
        l("onStart")
    }

    override fun onRestart() {
        super.onRestart()
        l("onRestart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        l("onNewIntent")
    }

    override fun onResume() {
        super.onResume()
        l("onResume")
    }

    override fun onPostResume() {
        super.onPostResume()
        l("onPostResume")
    }

    override fun onPause() {
        super.onPause()
        l("onPause")
    }

    override fun onStop() {
        super.onStop()
        l("onStop")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        l("onConfigurationChanged")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        l("onRestoreInstanceState")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        l("onSaveInstanceState")
    }

    // MENU TOP RIGHT
    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
        l("onMenuOpened")
        return super.onMenuOpened(featureId, menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        l("onCreateOptionsMenu")
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        l("onActivityResult")
    }
}