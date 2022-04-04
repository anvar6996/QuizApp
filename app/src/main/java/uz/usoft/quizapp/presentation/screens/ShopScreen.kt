package uz.usoft.quizapp.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.usoft.quizapp.R
import uz.usoft.quizapp.databinding.ScreenShopBinding

@AndroidEntryPoint
class ShopScreen : Fragment(R.layout.screen_shop) {
    private val bind by viewBinding(ScreenShopBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}