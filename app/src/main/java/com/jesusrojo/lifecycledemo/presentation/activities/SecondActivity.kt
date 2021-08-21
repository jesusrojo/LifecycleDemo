package com.jesusrojo.lifecycledemo.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.jesusrojo.lifecycledemo.databinding.SecondActivityLayoutBinding
import com.jesusrojo.lifecycledemo.utils.DebugHelp


open class SecondActivity: BaseActivity() {

    protected lateinit var binding: SecondActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        l("onCreate SecondActivity")

        binding = SecondActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
        setupToolbar()
    }
    protected open fun setupUi() {
        binding.textViewTitle.text = "Second Activity"

        binding.btn01.text = "Open Dialog"
        binding.btn01.setOnClickListener {

            val alertDialog = AlertDialog.Builder(this).create()
            alertDialog.setMessage("My message in Second Activity dialog")
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Close") { dialog, which ->
                dialog.dismiss()
            }
            alertDialog.show()
        }

        binding.btn02.text = "Go First Activity (finish())"
        binding.btn02.setOnClickListener { finish() }

        binding.btn03.text = "Go First Activity (intent and finish)"
        binding.btn03.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
        binding.btn04.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        binding.textViewLog.text = DebugHelp.fullLog
    }


    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        val myActionBar = supportActionBar
        myActionBar?.setDisplayHomeAsUpEnabled(true)
        myActionBar?.setHomeButtonEnabled(true)
        myActionBar?.setDisplayShowHomeEnabled(true)
        myActionBar?.setDisplayShowTitleEnabled(true)
        myActionBar?.title = javaClass.simpleName
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        l("onCreate onOptionsItemSelected")
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}