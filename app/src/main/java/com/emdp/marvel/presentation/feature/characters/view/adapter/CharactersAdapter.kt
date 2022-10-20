package com.emdp.marvel.presentation.feature.characters.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.emdp.marvel.R
import com.emdp.marvel.databinding.AdapterCharactersBinding
import com.emdp.marvel.presentation.domain.CharacterVo
import com.emdp.marvel.presentation.utils.glide

class CharactersAdapter(
    private val onCharacterClick: (CharacterVo) -> Unit
) : ListAdapter<CharacterVo, CharactersAdapter.CharactersViewHolder>(CharactersDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder =
        CharactersViewHolder(
            AdapterCharactersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(getItem(position), onCharacterClick)
    }

    object CharactersDiffCallback : DiffUtil.ItemCallback<CharacterVo>() {
        override fun areItemsTheSame(oldItem: CharacterVo, newItem: CharacterVo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CharacterVo, newItem: CharacterVo): Boolean =
            oldItem == newItem
    }

    inner class CharactersViewHolder(binding: AdapterCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val ivCharacter: ImageView = binding.ivCharacter
        private val tvCharacterName: TextView = binding.tvCharacterName
        private val rootCardView: CardView = binding.root
        private val context: Context = binding.root.context

        fun bind(character: CharacterVo, onCharacterClick: (CharacterVo) -> Unit) {
            context.glide(
                imageLoad = character.thumbnail,
                placeholder = R.drawable.ic_marvel,
                imageError = R.drawable.ic_marvel,
                imageView = ivCharacter
            )
            tvCharacterName.text = character.name
            rootCardView.setOnClickListener { onCharacterClick.invoke(character) }
        }
    }
}