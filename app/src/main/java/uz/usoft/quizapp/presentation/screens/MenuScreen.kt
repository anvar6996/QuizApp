package uz.usoft.quizapp.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import render.animations.Flip
import render.animations.Render
import render.animations.Rotate
import render.animations.Slide
import uz.usoft.quizapp.R
import uz.usoft.quizapp.app.App
import uz.usoft.quizapp.databinding.ScreenMenuBinding
import uz.usoft.quizapp.utils.scope

@AndroidEntryPoint
class MenuScreen : Fragment(R.layout.screen_menu) {
    private val bind by viewBinding(ScreenMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)

        bind.play.setOnClickListener {
            findNavController().navigate(R.id.action_menuScreen_to_mapLevelsScreen)

        }

        val renderAchivements = Render(requireContext())
        val renderShop = Render(requireContext())
// Set Animation
        renderAchivements.setAnimation(Rotate().InUpRight(achivementIcon))
        renderShop.setAnimation(Slide().InLeft(shopIcon))

        renderAchivements.start()
        renderShop.start()


        bind.achivement.setOnClickListener {
            findNavController().navigate(R.id.action_menuScreen_to_achivementsScreen)
        }

        bind.shop.setOnClickListener {
            findNavController().navigate(R.id.action_menuScreen_to_shopScreen)
        }
    }
}