package com.teegarcs.amifunny.android.create_joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.teegarcs.amifunny.android.AMIFunnyTheme
import com.teegarcs.amifunny.android.core.observeSideEffects
import com.teegarcs.amifunny.features.create_joke.CreateJokeSE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateJokeFragment : Fragment() {

    private val viewModel: CreateJokeViewModel by viewModels()
    private val createJokeUI: CreateJokeUI by lazy {
        CreateJokeUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AMIFunnyTheme {
                    val viewState = viewModel.state.collectAsState()
                    createJokeUI.BuildUI(state = viewState.value, viewModel::processIntent)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeSideEffects<CreateJokeSE>(this) {
            //TODO
        }
    }

}