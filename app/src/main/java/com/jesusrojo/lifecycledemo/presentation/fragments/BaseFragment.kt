package com.jesusrojo.lifecycledemo.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jesusrojo.lifecycledemo.databinding.ButtonsMessageLayoutBinding
import com.jesusrojo.lifecycledemo.utils.DebugHelp
import com.jesusrojo.lifecycledemo.utils.autoCleared

abstract class BaseFragment : Fragment() {

    private val myTag = javaClass.simpleName
    protected fun l(message: String) = DebugHelp.l(myTag, message)

    protected var binding: ButtonsMessageLayoutBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ButtonsMessageLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        l("onViewCreated**********")
        if(savedInstanceState == null){
            l("savedInstanceState is null")
        } else {
            l("savedInstanceState not null")
        }
    }

    // UI
    fun clearTextViewLog() {
        Toast.makeText(requireContext(), "clearTextViewLog", Toast.LENGTH_SHORT).show()
        binding.textViewLog.text = ""
    }

    private fun setTextTextViewLog(fullLog: String) {
        binding.textViewLog.text =  fullLog
    }

    //
    override fun onResume() {
        super.onResume()
        l("onResume")
        setTextTextViewLog(DebugHelp.fullLog)
    }



    // LIFECYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        l("onCreate")
    }

    override fun onStart() {
        super.onStart()
        l("onStart")
    }

    override fun onPause() {
        super.onPause()
        l("onPause")
    }

    override fun onStop() {
        super.onStop()
        l("onStop")

    }
    override fun onDestroy() {
        super.onDestroy()
        l("onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        l("onDestroyView")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        l("onAttach")

    }

    override fun onDetach() {
        super.onDetach()
        l("onDetach")
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        l("onViewStateRestored")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        l("onSaveInstanceState")
    }

    // DEPRECIATED
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        l("onActivityCreated (depreciated)")
//    }

//    override fun onAttach(activity: Activity) {
//        super.onAttach(activity)
//    }

}