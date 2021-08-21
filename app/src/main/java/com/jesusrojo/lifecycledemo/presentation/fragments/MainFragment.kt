package com.jesusrojo.lifecycledemo.presentation.fragments

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.jesusrojo.lifecycledemo.R
import com.jesusrojo.lifecycledemo.presentation.activities.MainActivity
import com.jesusrojo.lifecycledemo.presentation.activities.ForResultActivity
import com.jesusrojo.lifecycledemo.presentation.activities.SecondActivity
import com.jesusrojo.lifecycledemo.presentation.viewmodel.MainViewModel
import com.jesusrojo.lifecycledemo.presentation.presenter.MainPresenter

class MainFragment: BaseFragment() {

    companion object { var count = 0 }

    private lateinit var viewModel: MainViewModel
    private lateinit var presenter: MainPresenter

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//      //INITIATED IN onViewCreated LOG ONCREATE
//        presenter = MainPresenter(lifecycle)
//        requireActivity().lifecycle.addObserver(presenter)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        count++
        l("onCreateView $count")

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        presenter = MainPresenter()
        requireActivity().lifecycle.addObserver(presenter) // here log onCreate

        setupUi()
        setupMenuTopRight()
    }

    private fun setupMenuTopRight() {
        (activity as MainActivity).setupMenuOptionsMainFragment()
    }

    private fun setupUi() {
        binding.textViewTitle.text = "Main Fragment"

        binding.btn01.text = "Go Second Fragment (navigate)"
        binding.btn01.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_SecondFragment, null)
            // bundleOf("id" to myDataId))
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

        binding.btn04.text = "Go Activity For Result"
        binding.btn04.setOnClickListener { openActivityForResult() }
    }

    private fun openActivityForResult() {
        startForResult.launch(Intent(requireContext(), ForResultActivity::class.java)
            .putExtra(ForResultActivity.PARAM_SEND_KEY, "ParamX"))
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val extra = intent?.getStringExtra(ForResultActivity.PARAM_RETURNED_KEY)
            val message = "startForResult answer  Activity.RESULT_OK $extra"
            l(message)
            toast(message)
        }
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}