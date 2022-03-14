package uz.usoft.quizapp.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.usoft.quizapp.R
import uz.usoft.quizapp.data.response.level.Data
import uz.usoft.quizapp.data.response.level.LevelResponse
import uz.usoft.quizapp.databinding.ScreenLevelCategoryBinding
import uz.usoft.quizapp.presentation.adapters.levels.CategoryQuestionsAdapter
import uz.usoft.quizapp.presentation.viewmodels.questions.CategoryScreenViewModel
import uz.usoft.quizapp.presentation.viewmodelsimpl.questions.CategoryScreenViewModelImpl
import uz.usoft.quizapp.utils.scope
import uz.usoft.quizapp.utils.showToast

@AndroidEntryPoint
class CategoryScreen :Fragment(R.layout.screen_level_category){
    private val bind by viewBinding(ScreenLevelCategoryBinding::bind)
    private val adapterCategory=CategoryQuestionsAdapter()
    private val viewModel : CategoryScreenViewModel by viewModels<CategoryScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =bind.scope{
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val id=it.getString("id")
            viewModel.getQuestions(id.toString())
        }


        recyklerView.adapter = adapterCategory
        recyklerView.layoutManager = StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL)
        viewModel.errorFlow.onEach {
            showToast(it)
        }.launchIn(lifecycleScope)
        viewModel.progressFlow.onEach {

        }.launchIn(lifecycleScope)
        viewModel.successFlow.onEach {
            adapterCategory.submitList(it.data)
        }.launchIn(lifecycleScope)
    }
}