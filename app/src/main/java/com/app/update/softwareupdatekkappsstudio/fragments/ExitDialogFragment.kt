package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.app.update.softwareupdatekkappsstudio.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExitDialogFragment : BottomSheetDialogFragment() {
    interface ExitDialogListener {
        fun onExitConfirmed()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        val view = inflater.inflate(R.layout.fragment_bottom_sheet_exit_frgament, container, false)
        val titleTextView = view.findViewById<TextView>(R.id.exit_title)
        val messageTextView = view.findViewById<TextView>(R.id.exit_message)
        val yesButton = view.findViewById<Button>(R.id.yes_button)
        val noButton = view.findViewById<Button>(R.id.no_button)
        val nativeExit = view.findViewById<FrameLayout>(R.id.flNativeExit)
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        titleTextView.text = "Exit App"
        messageTextView.text = "Do you want to exit the app?"
        yesButton.setOnClickListener { v: View? ->
            requireActivity().finish()

        }
        noButton.setOnClickListener { v: View? -> dismiss() }
        return view
    }
}