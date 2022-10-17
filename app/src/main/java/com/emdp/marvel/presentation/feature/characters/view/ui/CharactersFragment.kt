package com.emdp.marvel.presentation.feature.characters.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.emdp.domain.domain.CharactersBo
import com.emdp.domain.feature.CharactersDomainBridge
import com.emdp.marvel.R
import com.emdp.marvel.databinding.FragmentCharactersBinding
import com.emdp.marvel.presentation.base.BaseMvvmView
import com.emdp.marvel.presentation.base.ScreenState
import com.emdp.marvel.presentation.feature.characters.view.state.CharactersState
import com.emdp.marvel.presentation.feature.characters.viewmodel.CharactersViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment :
    Fragment(),
    BaseMvvmView<CharactersViewModel, CharactersDomainBridge<CharactersBo>, CharactersState> {

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

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        viewModel.onViewCreated()
    }

    override fun processRenderState(renderState: CharactersState) {
        when(renderState) {
            is CharactersState.ShowCharactersList -> {}
            is CharactersState.ShowCharacterDetail -> {}
            is CharactersState.ShowError -> {}
        }
    }

    override fun initModel() {
        lifecycleScope.launch {
            viewModel.screenState.collect { screenState ->
                when(screenState) {
                    is ScreenState.Idle -> {}
                    is ScreenState.Loading -> {}
                    is ScreenState.Render<CharactersState> -> {
                        processRenderState(screenState.renderState)
                    }
                }
            }
        }
    }
}