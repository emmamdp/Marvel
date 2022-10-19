package com.emdp.marvel.presentation.feature.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emdp.marvel.R
import com.emdp.marvel.databinding.AdapterItemsBinding
import com.emdp.marvel.presentation.domain.ItemVo
import com.emdp.marvel.presentation.utils.CharacterEnum
import com.emdp.marvel.presentation.utils.glide

class ItemsAdapter(
    private val characterType: CharacterEnum
) : ListAdapter<ItemVo, ItemsAdapter.ItemsViewHolder>(CharactersDiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemsViewHolder =
        ItemsViewHolder(
            AdapterItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object CharactersDiffCallback : DiffUtil.ItemCallback<ItemVo>() {
        override fun areItemsTheSame(oldItem: ItemVo, newItem: ItemVo): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: ItemVo, newItem: ItemVo): Boolean =
            oldItem == newItem
    }

    inner class ItemsViewHolder(binding: AdapterItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val tvItem: TextView = binding.tvItem
        private val ivBubble: ImageView = binding.ivBubble
        private val context: Context = binding.root.context

        fun bind(item: ItemVo) {

            val image = when (characterType) {
                CharacterEnum.COMIC -> R.drawable.comic
                CharacterEnum.SERIE -> R.drawable.camera
                CharacterEnum.EVENT -> R.drawable.event
            }
            context.glide(image, R.drawable.ic_marvel, R.drawable.ic_marvel, ivBubble)
            tvItem.text = item.name
        }
    }
}