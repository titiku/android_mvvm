package com.example.android_mvvm

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_mvvm.databinding.ItemMobileImageBinding
import com.example.core.MobileImageEntity

class MobileImageAdapter() :
    ListAdapter<MobileImageEntity, MobileImageAdapter.MobileImageViewHolder>(MobileImageDiffUtils()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MobileImageViewHolder {
        return MobileImageViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.item_mobile_image,
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: MobileImageViewHolder, position: Int) {
        viewHolder.bindTo(getItem(position))
    }

    inner class MobileImageViewHolder(
        private val binding: ItemMobileImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(mobileImageEntity: MobileImageEntity) {
            Glide.with(binding.ivMobile.context)
                .load("https://www" + mobileImageEntity.url.substringAfter("www"))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivMobile)
        }
    }

    class MobileImageDiffUtils : DiffUtil.ItemCallback<MobileImageEntity>() {
        override fun areItemsTheSame(
            oldItem: MobileImageEntity,
            newItem: MobileImageEntity
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: MobileImageEntity,
            newItem: MobileImageEntity
        ) = oldItem == newItem
    }
}