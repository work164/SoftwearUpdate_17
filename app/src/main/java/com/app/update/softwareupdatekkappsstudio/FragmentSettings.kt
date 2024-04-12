package com.app.update.softwareupdatekkappsstudio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentSettingsBinding

class FragmentSettings : Fragment() {

    private var settingsBinding: FragmentSettingsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                        findNavController().popBackStack()

                }
            })
        settingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)

        return settingsBinding?.root!!
    }
}