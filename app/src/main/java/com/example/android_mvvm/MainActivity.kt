package com.example.android_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.android_mvvm.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var listener: PageChangeListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vpMobileList.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        val listTab = listOf("All", "Favourite")
        TabLayoutMediator(binding.tabHeader, binding.vpMobileList) { tab, position ->
            tab.text = listTab[position]
        }.attach()

        binding.vpMobileList.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val fragment = supportFragmentManager.findFragmentByTag("f$position")
                if (fragment is MainFragment) {
                    listener = fragment
                    listener?.onPageChangeListener()
                }
            }
        })
    }
}