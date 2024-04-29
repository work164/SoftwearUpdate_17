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
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentBottomSheetExitFrgamentBinding
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentDeviceInfoBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithMediaBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExitDialogFragment : BottomSheetDialogFragment() {
    interface ExitDialogListener {
        fun onExitConfirmed()
    }
    private lateinit var binding: FragmentBottomSheetExitFrgamentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        binding = FragmentBottomSheetExitFrgamentBinding.inflate(inflater,container,false)

      //  val view = inflater.inflate(R.layout.fragment_bottom_sheet_exit_frgament, container, false)
//        val titleTextView = view.findViewById<TextView>(R.id.exit_title)
//        val messageTextView = view.findViewById<TextView>(R.id.exit_message)
//        val yesButton = view.findViewById<Button>(R.id.yes_button)
//        val noButton = view.findViewById<Button>(R.id.no_button)
//        val nativeExit = view.findViewById<FrameLayout>(R.id.flNativeExit)
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        binding.exitTitle.text = "Exit App"
        binding.exitMessage.text = "Do you want to exit the app?"
        binding.yesButton.setOnClickListener { v: View? ->
            requireActivity().finish()

        }
        binding.noButton.setOnClickListener { v: View? -> dismiss() }
        loadAds()
        return binding.root
    }


    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.flNativeExit.visibility = View.VISIBLE
            val bindAdExit = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "Exit"
            ).setAdCallerName("Exit")
                .loadNativeAd(
                    getString(R.string.native_id),
                    Constants.val_native_exit,
                    binding.flNativeExit,
                    bindAdExit.root,
                    bindAdExit.adAppIcon,
                    bindAdExit.adHeadline,
                    bindAdExit.adBody,
                    bindAdExit.adCallToAction,
                    null,
                    null,
                    adFailed = {
                        binding.flNativeExit.visibility = View.GONE
                    },
                    adValidate = {
                        binding.flNativeExit.visibility = View.GONE

                    },
                    adClicked = {

                    },
                    adImpression = {

                    }
                )

        } else {
            binding.flNativeExit.visibility = View.GONE
        }
    }

}