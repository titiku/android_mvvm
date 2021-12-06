package com.example.android_mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_mvvm.databinding.ItemMobileBinding
import com.example.core.MobilesListEntity

class MobilesListAdapter : RecyclerView.Adapter<MobilesListAdapter.MobileViewHolder>() {
    private var list = listOf<MobilesListEntity>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MobileViewHolder {
        return MobileViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.item_mobile,
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: MobileViewHolder, position: Int) {
        viewHolder.bindTo(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setMobilesList(list: List<MobilesListEntity>) {
        this.list = list
    }

    class MobileViewHolder(
        private val binding: ItemMobileBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(mobilesListEntity: MobilesListEntity) {
            binding.model = mobilesListEntity
            Glide.with(binding.ivMobile.context)
                .load(mobilesListEntity.thumbImageURL)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivMobile)
        }
    }
}