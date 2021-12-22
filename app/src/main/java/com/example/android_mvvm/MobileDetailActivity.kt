package com.example.android_mvvm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_mvvm.databinding.ActivityMobileDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MobileDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMobileDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMobileDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(
                binding.fm.id,
                MobileDetailFragment.newInstance(
                    intent.extras?.getInt(MOBILE_ID) ?: 0,
                    intent.extras?.getString(NAME) ?: "",
                )
            ).commit()
    }


    companion object {
        private const val MOBILE_ID = "MOBILE_ID"
        private const val NAME = "NAME"

        fun createIntent(context: Context, mobileId: Int, name: String): Intent {
            return Intent(context, MobileDetailActivity::class.java).apply {
                putExtra(MOBILE_ID, mobileId)
                putExtra(NAME, name)
            }
        }
    }
}