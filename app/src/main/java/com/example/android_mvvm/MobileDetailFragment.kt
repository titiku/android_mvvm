package com.example.android_mvvm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_mvvm.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MobileDetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: MobileDetailViewModel
    private val adapter = MobileImageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MobileDetailViewModel::class.java]
        binding = FragmentDetailBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        initAdapter()
        subscribeObservers()
        viewModel.getMobile(getMobileId())
        viewModel.getMobileImage(getMobileId())
    }

    private fun initAdapter() {
        binding.rvImage.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvImage.adapter = adapter
    }

    private fun subscribeObservers() {
        viewModel.mobileImage.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setToolbar() {
        activity?.apply {
            setActionBar(binding.toolbar)
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.title = arguments?.getString(NAME) ?: ""
            binding.toolbar.setNavigationOnClickListener { finish() }
        }
    }

    private fun getMobileId(): Int {
        return arguments?.getInt(MOBILE_ID, 0) ?: 0
    }

    companion object {
        private const val MOBILE_ID = "MOBILE_ID"
        private const val NAME = "NAME"

        @JvmStatic
        fun newInstance(mobileId: Int, name: String) = MobileDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(MOBILE_ID, mobileId)
                putString(NAME, name)
            }
        }
    }
}