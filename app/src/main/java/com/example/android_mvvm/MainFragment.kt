package com.example.android_mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_mvvm.databinding.FragmentMainBinding
import com.example.core.MobileEntity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(), PageChangeListener {
    @Inject
    lateinit var string: String
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = MobilesListAdapter(::onClickFavorite, ::onClickMobile)

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
        viewModel.getMobilesList(getPageIndex())
    }

    override fun onPageChangeListener() {
        viewModel.getMobilesList(getPageIndex())
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

    private fun onClickFavorite(mobileEntity: MobileEntity) {
        viewModel.clickFavorite(mobileEntity, getPageIndex())
    }

    private fun onClickMobile(mobileEntity: MobileEntity) {
        activity?.apply {
            startActivity(
                MobileDetailActivity.createIntent(
                    this,
                    mobileEntity.id,
                    mobileEntity.name
                )
            )
        }
    }

    private fun getPageIndex(): Int {
        return arguments?.getInt(PAGE_INDEX, 0) ?: 0
    }

    companion object {
        private const val PAGE_INDEX = "PAGE_INDEX"

        @JvmStatic
        fun newInstance(pageIndex: Int) = MainFragment().apply {
            arguments = Bundle().apply {
                putInt(PAGE_INDEX, pageIndex)
            }
        }
    }
}