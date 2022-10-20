package com.emdp.marvel.presentation.feature.characters.view.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.feature.CharactersDomainBridge
import com.emdp.marvel.databinding.FragmentCharactersBinding
import com.emdp.marvel.presentation.base.BaseMvvmView
import com.emdp.marvel.presentation.base.ScreenState
import com.emdp.marvel.presentation.domain.FailureVo
import com.emdp.marvel.presentation.feature.characters.view.adapter.CharactersAdapter
import com.emdp.marvel.presentation.feature.characters.view.listener.AdapterScrollListener
import com.emdp.marvel.presentation.feature.characters.view.state.CharactersState
import com.emdp.marvel.presentation.feature.characters.viewmodel.CharactersViewModel
import com.emdp.marvel.presentation.feature.common.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment :
    BaseFragment(),
    BaseMvvmView<CharactersViewModel, CharactersDomainBridge<CharactersBo>, CharactersState> {

    private var isLoading = false
    private val adapterCharactersList = CharactersAdapter(
        onCharacterClick = {
            findNavController().navigate(
                CharactersFragmentDirections.actionCharactersFragmentToDetailFragment(it)
            )
        }
    )

    override val viewModel: CharactersViewModel by viewModel()
    private val binding: FragmentCharactersBinding by lazy {
        FragmentCharactersBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initModel()
        initData()
        initView()
    }

    override fun onResume() {
        super.onResume()
        hideBackArrow()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun processRenderState(renderState: CharactersState) {
        when (renderState) {
            is CharactersState.ShowCharactersList -> {
                adapterCharactersList.apply {
                    submitList(renderState.charactersList)
                    notifyDataSetChanged()
                }
                isLoading = false
            }
            is CharactersState.ShowError -> {
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
                    is ScreenState.Render<CharactersState> -> {
                        processRenderState(screenState.renderState)
                        hideProgressBar()
                    }
                }
            }
        }
    }

    private fun initView() {
        with(binding.rvCharacters) {
            adapter = adapterCharactersList
            val llm = layoutManager as LinearLayoutManager
            addOnScrollListener(object : AdapterScrollListener(llm) {

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {
                    isLoading = true
                    viewModel.moreCharacters()
                }
            })
        }
    }

    private fun initData() {
        if (!isOnBackPressed()) viewModel.onViewCreated() else resetOnBackPressed()
    }

    private fun showError(failure: FailureVo?) {
        failure?.let {
            showSnackbarError(it.getErrorMessage())
        }
    }
}