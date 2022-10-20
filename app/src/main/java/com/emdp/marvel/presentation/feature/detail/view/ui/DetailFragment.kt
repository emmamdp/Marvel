package com.emdp.marvel.presentation.feature.detail.view.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emdp.marvel.R
import com.emdp.marvel.databinding.FragmentDetailBinding
import com.emdp.marvel.presentation.domain.CharacterVo
import com.emdp.marvel.presentation.domain.ItemVo
import com.emdp.marvel.presentation.feature.common.BaseFragment
import com.emdp.marvel.presentation.feature.detail.adapter.ItemXXXAdapter
import com.emdp.marvel.presentation.feature.detail.adapter.ItemsAdapter
import com.emdp.marvel.presentation.utils.CharacterEnum
import com.emdp.marvel.presentation.utils.glide
import com.emdp.marvel.presentation.utils.isShow


class DetailFragment : BaseFragment() {

    private lateinit var character: CharacterVo

    private val adapterSeries = ItemsAdapter(CharacterEnum.SERIE)
    private val adapterComics = ItemsAdapter(CharacterEnum.COMIC)
    private val adapterEvents = ItemsAdapter(CharacterEnum.EVENT)
    private val adapterStories = ItemXXXAdapter()

    //    private val args: DetailFragmentArgs by navArgs()
    private val binding: FragmentDetailBinding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showBackArrow()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
//        character = args.character
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        loadCharacter()
        with(binding) {
            character.comics.items?.let {
                loadData(
                    character.comics.available,
                    it,
                    adapterComics,
                    rvComics,
                    tvComics
                )
            }
            character.series.items?.let {
                loadData(
                    character.series.available,
                    it,
                    adapterSeries,
                    rvSeries,
                    tvSeries
                )
            }
            character.events.items?.let {
                loadData(
                    character.events.available,
                    it,
                    adapterEvents,
                    rvEvents,
                    tvEvents
                )
            }

        }
        loadStories()
    }

    private fun loadCharacter() {
        with(binding) {
            requireContext().glide(
                imageLoad = character.thumbnail,
                placeholder = R.drawable.ic_marvel,
                imageError = R.drawable.ic_marvel,
                imageView = ivCharacter
            )
            tvCharacterName.text = character.name
            if (character.description.isNotEmpty()) {
                tvCharacterDescription.text = character.description
            } else {
                tvCharacterDescription.isShow(show = false)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadData(
        available: Int,
        items: List<ItemVo>,
        adapter: ItemsAdapter,
        recyclerView: RecyclerView,
        textView: TextView
    ) {
        if (available > 0 && items.isNotEmpty()) {
            adapter.apply {
                recyclerView.adapter = this
                submitList(items)
                notifyDataSetChanged()
            }
        } else {
            textView.isShow(show = false)
            recyclerView.isShow(show = false)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadStories() {
        with(binding) {
            if (character.stories.available > 0 && !character.stories.items.isNullOrEmpty()) {
                adapterStories.apply {
                    rvStories.adapter = this
                    submitList(character.stories.items)
                    notifyDataSetChanged()
                }
            } else {
                tvStories.isShow(show = false)
                rvStories.isShow(show = false)
            }
        }
    }
}