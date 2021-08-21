package com.jesusrojo.lifecycledemo.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.jesusrojo.lifecycledemo.presentation.activities.MainActivity
import com.jesusrojo.lifecycledemo.presentation.activities.SecondActivity


class ThirdFragment: BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        l("onCreateView")
        setupUi()
        setupMenuTopRight()
    }

    private fun setupMenuTopRight() {
        (activity as MainActivity).setupMenuOptionsThirdFragment()
    }
    private fun setupUi() {
        binding.textViewTitle.text = "Third Fragment"

        binding.btn01.text = "Open Dialog"
        binding.btn01.setOnClickListener {

            val alertDialog = AlertDialog.Builder(requireActivity()).create()
            alertDialog.setMessage("My message in Third Fragment")
            alertDialog.setButton(
                AlertDialog.BUTTON_POSITIVE, "Close") { dialog, which ->
                dialog.dismiss()
            }
            alertDialog.show()
        }

        binding.btn02.text = "Go Second Activity (intent, not finish)"
        binding.btn02.setOnClickListener {
            requireContext().startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        binding.btn03.text = "Go Second Activity (intent & finish)"
        binding.btn03.setOnClickListener {
            requireContext().startActivity(Intent(requireContext(), SecondActivity::class.java))
            requireActivity().finish()
        }
        binding.btn04.visibility = View.GONE
    }

    fun onClickMenuTopRightThirdFragmentItem1() {
        Toast.makeText(requireContext(), "Clicked from third fragment" , Toast.LENGTH_SHORT).show()
    }
}