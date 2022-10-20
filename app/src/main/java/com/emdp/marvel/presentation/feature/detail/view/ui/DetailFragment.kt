package com.emdp.marvel.presentation.feature.detail.view.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.feature.CharactersDomainBridge
import com.emdp.marvel.R
import com.emdp.marvel.databinding.FragmentDetailBinding
import com.emdp.marvel.presentation.base.BaseMvvmView
import com.emdp.marvel.presentation.base.ScreenState
import com.emdp.marvel.presentation.domain.CharacterVo
import com.emdp.marvel.presentation.domain.FailureVo
import com.emdp.marvel.presentation.domain.ItemVo
import com.emdp.marvel.presentation.feature.common.BaseFragment
import com.emdp.marvel.presentation.feature.detail.adapter.ItemsAdapter
import com.emdp.marvel.presentation.feature.detail.view.state.DetailState
import com.emdp.marvel.presentation.feature.detail.viewmodel.DetailViewModel
import com.emdp.marvel.presentation.utils.CharacterEnum
import com.emdp.marvel.presentation.utils.glide
import com.emdp.marvel.presentation.utils.isShow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment :
    BaseFragment(),
    BaseMvvmView<DetailViewModel, CharactersDomainBridge<CharactersBo>, DetailState> {

    private val args: DetailFragmentArgs by navArgs()
    override val viewModel: DetailViewModel by viewModel()
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
        initModel()
        initData()
    }

    override fun processRenderState(renderState: DetailState) {
        when (renderState) {
            is DetailState.ShowCharacterDetail -> {
                loadView(renderState.character)
            }
            is DetailState.ShowError -> {
                showError(renderState.failure)
            }
        }
    }

    override fun initModel() {
        lifecycleScope.launch {
            viewModel.screenState.collect { screenState ->
                when (screenState) {
                    is ScreenState.Idle -> {}
                    is ScreenState.Loading -> showProgressBar()
                    is ScreenState.Render<DetailState> -> {
                        processRenderState(screenState.renderState)
                        hideProgressBar()
                    }
                }
            }
        }
    }

    private fun initData() {
        viewModel.onViewCreated(args.characterId)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadView(character: CharacterVo) {
        loadCharacter(character)
        with(binding) {
            character.comics.items?.let {
                loadData(
                    character.comics.available,
                    it,
                    ItemsAdapter(CharacterEnum.COMIC),
                    rvComics,
                    tvComics
                )
            }
            character.series.items?.let {
                loadData(
                    character.series.available,
                    it,
                    ItemsAdapter(CharacterEnum.SERIE),
                    rvSeries,
                    tvSeries
                )
            }
            character.events.items?.let {
                loadData(
                    character.events.available,
                    it,
                    ItemsAdapter(CharacterEnum.EVENT),
                    rvEvents,
                    tvEvents
                )
            }
            character.stories.items?.let {
                loadData(
                    character.stories.available,
                    it,
                    ItemsAdapter(CharacterEnum.STORIES),
                    rvStories,
                    tvStories
                )
            }
        }
    }

    private fun loadCharacter(character: CharacterVo) {
        with(binding) {
            ivCharacter.isShow(show = true)
            clCharacterDetail.isShow(show = true)

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

    private fun showError(failure: FailureVo?) {
        failure?.let {
            showSnackbarError(it.getErrorMessage())
        }
    }
}