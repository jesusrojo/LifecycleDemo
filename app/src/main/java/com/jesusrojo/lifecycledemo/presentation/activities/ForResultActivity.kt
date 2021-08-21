package com.jesusrojo.lifecycledemo.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast


class ForResultActivity: SecondActivity() {

    companion object{
        const val PARAM_SEND_KEY = "PARAM_SEND_KEY"
        const val PARAM_RETURNED_KEY = "PARAM_RETURNED_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        l("onCreate ForResultActivity")

        val intent = intent

        if(intent.hasExtra(PARAM_SEND_KEY)) {
            val extra  = intent.getStringExtra(PARAM_SEND_KEY)
            l("intent extra: $extra")
            Toast.makeText(this, extra, Toast.LENGTH_SHORT).show()
        }
    }

    override fun setupUi() {
        binding.textViewTitle.text = "ForResultActivity"

        binding.btn01.visibility = View.GONE
        binding.btn02.visibility = View.GONE
        binding.btn03.visibility = View.GONE

        binding.btn04.text = "Send results"
        binding.btn04.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra(PARAM_RETURNED_KEY, "my result")
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}