@file:Suppress("RedundantNullableReturnType")

package com.jesusrojo.lifecycledemo.presentation.dialogfragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.jesusrojo.lifecycledemo.R
import com.jesusrojo.lifecycledemo.databinding.ButtonsMessageLayoutBinding

import timber.log.Timber

class DetailsDialogFragment : DialogFragment() {

    private lateinit var binding: ButtonsMessageLayoutBinding
    private var avatarUrl = ""
    private var details = ""

    private fun initAllArguments() {
        val arguments = arguments
        if (arguments != null) {
            avatarUrl = arguments.getString(ARG_AVATAR_URL)!!
            details = arguments.getString(ARG_DETAILS)!!
        }
    }

    @MainThread
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = ButtonsMessageLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllArguments()
        initUi()
    }

    private fun initUi() {
        binding.textViewTitle.text = "Details Dialog Fragment"

        binding.btn01.text = "Close dialog"
        binding.btn01.setOnClickListener { dismissMyDialog() }
        binding.btn01.gravity = Gravity.CENTER_VERTICAL
        binding.btn01.setCompoundDrawablesWithIntrinsicBounds(0, 0,
            android.R.drawable.ic_menu_close_clear_cancel, 0)

        binding.btn02.visibility = View.GONE
        binding.btn03.visibility = View.GONE

        val textUi = fullText
        binding.textViewLog.text = textUi
//       Glide
//        .with(binding.imageViewAvatarDetails.context)// without HILT
//        GlideApp
//            .with(binding.imageViewAvatarDetails.context) // with HILT
//            .load(avatarUrl)
//            .into(binding.imageViewAvatarDetails)

    }

    override fun onResume() {
        super.onResume()
        setDialogMatchWrap()
    }

    private fun setDialogMatchWrap() {
        val dialog = dialog
        if (dialog != null) {
            val window = dialog.window
            if (window != null) {
                val params = window.attributes
                if (params != null) {
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    window.attributes = params
                }
            }
        }
    }

    private val fullText: String
        get() {
            val activity = activity ?: return ""
            val resources = activity.resources
            val sb = StringBuilder()
            sb.append("*** ")
                .append(resources.getString(R.string.details))
                .append(" ***\n\n")
                .append(details)
                .append("\n")
            return sb.toString()
        }

    private fun dismissMyDialog() {
        try {
            dismiss()
        } catch (e: Exception) {
            Timber.e("ko ## $e")
        }
    }


    companion object {
        private fun showMyFragment(a: AppCompatActivity?,
                                     fragment: DialogFragment?) {
            if (a == null || fragment == null) return
            try {
                val aClass: Class<out DialogFragment> = fragment.javaClass
                if (aClass != null) {
                    val simpleName = aClass.simpleName
                    if (simpleName != null) {
                        val tag = simpleName + "_tag"
                        fragment.show(a.supportFragmentManager, tag)
                    }
                }
            } catch (e: IllegalStateException) { //error developer
                Timber.e("ko ## $e")
            } catch (e: Exception) {
                Timber.e("ko ## $e")
            }
        }

        fun showDetailsDialogFragment(a: AppCompatActivity,
                                      avatarUrl: String,
                                      details: String) {
            Timber.d("showDetailsDialogFragment ##")
            showMyFragment(a, newInstance(avatarUrl, details))
        }

        private fun newInstance(avatarUrl: String,
                                details: String): DetailsDialogFragment {
            val frag = DetailsDialogFragment()
            val args = Bundle()
            args.putString(ARG_AVATAR_URL, avatarUrl)
            args.putString(ARG_DETAILS, details)
            frag.arguments = args
            return frag
        }
        private const val ARG_AVATAR_URL = "ARG_AVATAR_URL"
        private const val ARG_DETAILS = "ARG_DETAILS"
    }
}
