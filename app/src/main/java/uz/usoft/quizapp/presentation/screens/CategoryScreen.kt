package uz.usoft.quizapp.presentation.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.usoft.quizapp.R
import uz.usoft.quizapp.data.others.StaticValues
import uz.usoft.quizapp.data.response.category.CategoryResponse
import uz.usoft.quizapp.data.response.category.Data
import uz.usoft.quizapp.databinding.ScreenLevelCategoryBinding
import uz.usoft.quizapp.presentation.adapters.levels.CategoryQuestionsAdapter
import uz.usoft.quizapp.presentation.viewmodels.questions.CategoryScreenViewModel
import uz.usoft.quizapp.presentation.viewmodelsimpl.questions.CategoryScreenViewModelImpl
import uz.usoft.quizapp.utils.scope
import uz.usoft.quizapp.utils.showToast

@AndroidEntryPoint
class CategoryScreen : Fragment(R.layout.screen_level_category) {
    private val bind by viewBinding(ScreenLevelCategoryBinding::bind)
    private val adapterCategory = CategoryQuestionsAdapter()
    private val viewModel: CategoryScreenViewModel by viewModels<CategoryScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val id = it.getString("id")
            if (id == "play") {
                viewModel.getPlay()
            } else {
                viewModel.getQuestions(id.toString())
            }
        }


        recyklerView.adapter = adapterCategory
        recyklerView.layoutManager =
            StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL)

        adapterCategory.setListener {
            showToast(it.toString())
        }

        viewModel.progressFlow.onEach {
            if (it) {
//                loadingBubbles.playAnimation()
                getVisible().let { visible ->
                    screenLoad.commentText.visibility = visible
                    screenLoad.connextText.visibility = visible
                    screenLoad.levelText.visibility = visible
                    screenLoad.loadScreenBg.visibility = visible
                }
            } else {
//                delay(3000)
                getGone().let { gone ->
                    screenLoad.commentText.visibility = gone
                    screenLoad.connextText.visibility = gone
                    screenLoad.levelText.visibility = gone
                    screenLoad.loadScreenBg.visibility = gone
                }
//                loadingBubbles.visibility = View.GONE
            }
        }.launchIn(lifecycleScope)
        viewModel.errorFlow.onEach {
            showToast(it)
        }.launchIn(lifecycleScope)

        viewModel.successFlow.onEach {
//            showToast(it.size.toString())
            adapterCategory.submitList(it)
        }.launchIn(lifecycleScope)

        adapterCategory.setListener {
            val bundle = Bundle()
//            bundle.putSerializable("value", it)
            showToast(it.answers.toString())
            StaticValues.questionAnswers = it
            findNavController().navigate(R.id.action_drawerCategoryScreen_to_questionScreen)
        }
    }


    private fun getGone() = View.GONE
    private fun getVisible() = View.VISIBLE
    private fun getInvisible() = View.INVISIBLE

}