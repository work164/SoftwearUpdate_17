package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.AvailableUpdateAdapter
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentUpdateAvailableBinding
import com.app.update.softwareupdatekkappsstudio.utils.navigateFragment
import com.app.update.softwareupdatekkappsstudio.view_model.ViewModelclass


class UpdateAvailableFragment : Fragment() {
    private var binding: FragmentUpdateAvailableBinding? = null
    lateinit var viewModel: ViewModelclass

    private val appAdapter by lazy {
        AvailableUpdateAdapter(requireContext(), onItemClick = {
            navigateFragment(
                R.id.availableAppsUpdateFragment,
                R.id.action_availableAppsUpdateFragment_to_appDetailFragment,
                Bundle().apply {
                    putString("appPackageName", it.packageName)
                }
            )
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })
        // Inflate the layout for this fragment
        binding = FragmentUpdateAvailableBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewModelclass::class.java]
        viewModel.infoList1.value = ScanAppFragment.updateAvailableList
        setUpClickListeners()
        setUpRecyclerView()
        onserveViewModel()

    }

    private fun onserveViewModel() {
        viewModel.infoList1.observe(viewLifecycleOwner) {
            appAdapter.setUpArrayList(it)
        }
    }

    private fun setUpRecyclerView() {
        binding?.run {
            rvUpdateAvailable.layoutManager = LinearLayoutManager(requireContext())
            rvUpdateAvailable.adapter = appAdapter
        }
    }

    private fun setUpClickListeners() {
        binding?.run {
            backDevice.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }


}