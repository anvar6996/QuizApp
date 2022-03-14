package uz.usoft.quizapp.presentation.screens

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
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
//        load()
        recyklerView.adapter = maplevelsAdapter
        recyklerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getMapLevels()
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        recyklerView.layoutManager = linearLayoutManager
        maplevelsAdapter.setListener {
            val bundle = Bundle()
            bundle.putString("id", it.toString())
            findNavController().navigate(R.id.action_mapLevelsScreen_to_categoryScreen, bundle)
        }

        maplevelsAdapter.submitList(list)
        viewModel.errorFlow.onEach {
            showToast(it)
        }.launchIn(lifecycleScope)
        viewModel.progressFlow.onEach {
            if(it)
            {
                bind.progress.show()
            }else
            {
                progress.hide()
            }
        }.launchIn(lifecycleScope)
        viewModel.successFlow.onEach {
            showToast(it.size.toString())
            maplevelsAdapter.submitList(it)
        }.launchIn(lifecycleScope)


    }

    private fun load() {
        for (i in 0 until 10) {
            list.add(MapLevelsResponseItem(i, "1", true))
        }
    }
}