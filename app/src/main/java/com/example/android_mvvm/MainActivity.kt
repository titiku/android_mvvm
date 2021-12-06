package com.example.android_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_mvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var string: String
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = MobilesListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        subscribeObservers()
        initView()
        viewModel.getMobilesList()
    }

    private fun initView() {
        binding.rvMobilesList.layoutManager = LinearLayoutManager(this)
        binding.rvMobilesList.adapter = adapter
    }

    private fun subscribeObservers() {
        viewModel.mobilesList.observe(this) {
            adapter.setMobilesList(it)
            adapter.notifyDataSetChanged()
        }
    }
}