package uz.usoft.quizapp.presentation.screens

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.blurry.Blurry
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.usoft.quizapp.R
import uz.usoft.quizapp.data.response.map.MapLevelsResponseItem
import uz.usoft.quizapp.databinding.ScreenMapLevelesBinding
import uz.usoft.quizapp.presentation.adapters.levels.MapLevelsAdapter
import uz.usoft.quizapp.presentation.viewmodels.questions.MapLevelsScreenViewModel
import uz.usoft.quizapp.presentation.viewmodelsimpl.questions.MapLevelsScreenViewModelImpl
import uz.usoft.quizapp.utils.scope
import uz.usoft.quizapp.utils.showToast


@AndroidEntryPoint
class MapLevelsScreen : Fragment(R.layout.screen_map_leveles) {
    private val bind by viewBinding(ScreenMapLevelesBinding::bind)
    private val maplevelsAdapter = MapLevelsAdapter()
    private val list = ArrayList<MapLevelsResponseItem>()
    private val viewModel: MapLevelsScreenViewModel by viewModels<MapLevelsScreenViewModelImpl>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)

//        val bitmap = Blurry.with(requireContext())
//            .radius(10)
//            .sampling(8)
//            .capture(blurBg).get()
//        blurBg.setImageDrawable(BitmapDrawable(resources, bitmap))


        recyklerView.adapter = maplevelsAdapter
        recyklerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getMapLevels()

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        recyklerView.layoutManager = linearLayoutManager
        val animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.circle_exploin_animation)
                .apply {
                    duration = 500
                    interpolator = AccelerateDecelerateInterpolator()
                }

        maplevelsAdapter.setListener {
            val bundle = Bundle()
            bundle.putString("id", it.toString())
            bind.recyklerView.startAnimation(animation)

            findNavController().navigate(
                R.id.action_mapLevelsScreen_to_drawerCategoryScreen,
                bundle
            )

        }

        viewModel.errorFlow.onEach {
            showToast(it)
        }.launchIn(lifecycleScope)


        viewModel.progressFlow.onEach {
            if (it) {
                circleLoad.visibility = View.VISIBLE
                circleLoad.playAnimation()

            } else {
                delay(3000)
                progress.hide()
                circleLoad.visibility = View.GONE
            }
        }.launchIn(lifecycleScope)
        viewModel.successFlow.onEach {
            maplevelsAdapter.submitList(it)
            recyklerView.scrollToPosition(1)
        }.launchIn(lifecycleScope)


    }

}