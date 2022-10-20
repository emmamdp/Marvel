package com.emdp.marvel.presentation.feature.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emdp.marvel.databinding.AdapterItemsxxxBinding
import com.emdp.marvel.presentation.domain.ItemVo

// FIXME - eliminar esta clase
class ItemXXXAdapter :
    ListAdapter<ItemVo, ItemXXXAdapter.ItemXXXViewHolder>(CharactersDiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemXXXViewHolder =
        ItemXXXViewHolder(
            AdapterItemsxxxBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemXXXViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object CharactersDiffCallback : DiffUtil.ItemCallback<ItemVo>() {
        override fun areItemsTheSame(oldItem: ItemVo, newItem: ItemVo): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: ItemVo, newItem: ItemVo): Boolean =
            oldItem == newItem
    }

    inner class ItemXXXViewHolder(binding: AdapterItemsxxxBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val tvItem: TextView = binding.tvItem
        private val tvItemType: TextView = binding.tvItemType

        @SuppressLint("SetTextI18n")
        fun bind(item: ItemVo) {
            tvItem.text = item.name
            tvItemType.text = "- ${item.type}"
        }
    }
}