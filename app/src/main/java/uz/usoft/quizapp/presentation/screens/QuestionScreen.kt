package uz.usoft.quizapp.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quizapp.R
import com.example.quizapp.databinding.ScreenQuestionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionScreen :Fragment(R.layout.screen_question) {
    private val bind by viewBinding(ScreenQuestionBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}