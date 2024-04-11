package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.LanguageAdaptor
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentLanguageBinding
import com.app.update.softwareupdatekkappsstudio.utils.setLocale


class LanguageFragment : Fragment() {
    private lateinit var binding: FragmentLanguageBinding
    private var languageCode = "en"

    private val languageAdaptor by lazy {
        LanguageAdaptor(requireContext(),  "en", onItemClick = {
            languageCode = it.languageCode
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (arguments?.getString("from") != "SplashFragment") {
                        findNavController().popBackStack()
                    }
                }
            })

        binding = FragmentLanguageBinding.inflate(inflater, container, false)

        setUpClickListeners()
        setUpRecyclerView()


        return binding.root
    }

    private fun setUpClickListeners() {
        binding.run {
            ifvBack.apply {
                visibility = if (arguments?.getString("from") != "SplashFragment") {
                    View.VISIBLE
                }else{
                    View.GONE
                }
                setOnClickListener {
                    findNavController().popBackStack()

                }
            }
            binding.changeLanguage.setOnClickListener{
                //sharedPreference.isLanguageCode = languageCode
                setLocale(requireContext(), languageCode)
                if (arguments?.getString("from") == "SplashFragment") {
                    findNavController().navigate(R.id.action_languageFragment_to_privacyFragment)

                } else {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.rvLanguage.layoutManager = GridLayoutManager(requireContext(),3)
        binding.rvLanguage.adapter = languageAdaptor
    }

}