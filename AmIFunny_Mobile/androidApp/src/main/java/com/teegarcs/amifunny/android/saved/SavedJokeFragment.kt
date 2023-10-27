package com.teegarcs.amifunny.android.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.teegarcs.amifunny.android.theme.FunnyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedJokeFragment : Fragment() {

//    private val viewModel: CreateJokeViewModel by viewModels()
//    private val createJokeUI: CreateJokeUI by lazy {
//        CreateJokeUI()
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FunnyTheme {
//                    val viewState = viewModel.state.collectAsState()
//                    createJokeUI.BuildUI(state = viewState.value, viewModel::processIntent)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.observeSideEffects<CreateJokeSE>(this) {
//            TODO
//        }
    }

}