package com.example.android_mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_mvvm.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {
    @Inject
    lateinit var string: String
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = MobilesListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = FragmentMainBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initView()
        viewModel.getMobilesList()
    }

    private fun initView() {
        binding.rvMobilesList.layoutManager = LinearLayoutManager(context)
        binding.rvMobilesList.adapter = adapter
    }

    private fun subscribeObservers() {
        viewModel.mobilesList.observe(viewLifecycleOwner) {
            adapter.setMobilesList(it)
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}