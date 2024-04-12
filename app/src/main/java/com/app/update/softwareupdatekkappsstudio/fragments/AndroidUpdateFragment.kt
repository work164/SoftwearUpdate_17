package com.app.update.softwareupdatekkappsstudio.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R

class AndroidUpdateFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()

                }
            })


        return inflater.inflate(R.layout.fragment_android_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.btnOSNext).setOnClickListener {
            try {
                openSystemUpdateSettings()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openSystemUpdateSettings() {
        try {
            requireActivity().startActivityForResult(
                Intent("android.settings.SYSTEM_UPDATE_SETTINGS"),
                0
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }
}