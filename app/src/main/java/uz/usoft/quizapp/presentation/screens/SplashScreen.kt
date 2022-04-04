package uz.usoft.quizapp.presentation.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.usoft.quizapp.R
import uz.usoft.quizapp.presentation.viewmodelsimpl.menu.SplashViewModelImpl

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveData.observe(this, observerClose)
    }

    private val observerClose = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_menuScreen)
    }
}