package uz.usoft.quizapp.presentation.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
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
import uz.usoft.quizapp.databinding.ScreenDrawerBinding
import uz.usoft.quizapp.presentation.adapters.levels.CategoryQuestionsAdapter
import uz.usoft.quizapp.presentation.viewmodels.questions.CategoryScreenViewModel
import uz.usoft.quizapp.presentation.viewmodelsimpl.questions.CategoryScreenViewModelImpl
import uz.usoft.quizapp.utils.scope
import uz.usoft.quizapp.utils.showToast

@AndroidEntryPoint
class DrawerCategoryScreen : Fragment(R.layout.screen_drawer) {
    private val bind by viewBinding(ScreenDrawerBinding::bind)
    private val adapterCategory = CategoryQuestionsAdapter()
    private val viewModel: CategoryScreenViewModel by viewModels<CategoryScreenViewModelImpl>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPassedData()
    }

    @SuppressLint("WrongConstant")
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

//        viewModel.successPassedFlow.onEach {
//            adapterCategory.list = it
//            showToast(it.size.toString())
//        }.launchIn(lifecycleScope)
        showToast(StaticValues.counter.toString())
        bind.include.menu.setOnClickListener {
            drawerLayout.openDrawer(Gravity.START)
        }
        bind.include.back.setOnClickListener {
            findNavController().popBackStack()
        }
        drawerNavView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.shopMenu -> {
                    findNavController().navigate(R.id.action_drawerCategoryScreen_to_shopScreen)
                    true
                }

                R.id.achivementMenu -> {
                    findNavController().navigate(R.id.action_drawerCategoryScreen_to_achivementsScreen)
                    true
                }
                else -> false
            }
        }
        include.recyklerView.adapter = adapterCategory
        include.recyklerView.layoutManager =
            StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL)

        adapterCategory.setListener {
            showToast(it.toString())
        }

        viewModel.progressFlow.onEach {
            if (it) {
//                loadingBubbles.playAnimation()
                getVisible().let { visible ->
                    include.screenLoad.commentText.visibility = visible
                    include.screenLoad.connextText.visibility = visible
                    include.screenLoad.levelText.visibility = visible
                    include.screenLoad.loadScreenBg.visibility = visible
                }
            } else {
//                delay(3000)
                getGone().let { gone ->
                    include.screenLoad.commentText.visibility = gone
                    include.screenLoad.connextText.visibility = gone
                    include.screenLoad.levelText.visibility = gone
                    include.screenLoad.loadScreenBg.visibility = gone
                }
//                loadingBubbles.visibility = View.GONE
            }
        }.launchIn(lifecycleScope)
        viewModel.errorFlow.onEach {
            showToast(it)
        }.launchIn(lifecycleScope)

        viewModel.successFlow.onEach {
            adapterCategory.submitList(it)
        }.launchIn(lifecycleScope)

        adapterCategory.setListener {
            val bundle = Bundle()
//            bundle.putSerializable("value", it)
            StaticValues.questionAnswers = it
            findNavController().navigate(R.id.action_drawerCategoryScreen_to_questionScreen, bundle)
        }
    }


    private fun getGone() = View.GONE
    private fun getVisible() = View.VISIBLE
    private fun getInvisible() = View.INVISIBLE

}