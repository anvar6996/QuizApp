package uz.usoft.quizapp.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.usoft.quizapp.R
import uz.usoft.quizapp.databinding.ScreenMenuBinding

@AndroidEntryPoint
class MenuScreen : Fragment(R.layout.screen_menu) {
    private val bind by viewBinding(ScreenMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.play.setOnClickListener {
            findNavController().navigate(R.id.action_menuScreen_to_mapLevelsScreen)
        }
    }
}