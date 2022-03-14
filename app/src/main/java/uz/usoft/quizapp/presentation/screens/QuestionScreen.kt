package uz.usoft.quizapp.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.usoft.quizapp.R
import uz.usoft.quizapp.data.domain.repository.QuestionsRepository
import uz.usoft.quizapp.data.domain.repositoryimpl.QuestionsRepositoryImpl
import uz.usoft.quizapp.databinding.ScreenQuestionBinding
import uz.usoft.quizapp.presentation.viewmodels.questions.QuestionsScreenViewModel
import uz.usoft.quizapp.presentation.viewmodelsimpl.questions.QuestionsScreenViewModelImpl

@AndroidEntryPoint
class QuestionScreen : Fragment(R.layout.screen_question) {
    private val bind by viewBinding(ScreenQuestionBinding::bind)
    private val viewModel: QuestionsScreenViewModel by viewModels<QuestionsScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}