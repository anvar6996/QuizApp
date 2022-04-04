package uz.usoft.quizapp.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.usoft.quizapp.R
import uz.usoft.quizapp.data.response.StatisticData
import uz.usoft.quizapp.databinding.ScreenAchievementsBinding
import uz.usoft.quizapp.presentation.adapters.achivements.AchivementsAdapter
import uz.usoft.quizapp.utils.scope

@AndroidEntryPoint
class AchivementsScreen : Fragment(R.layout.screen_achievements) {
    private val bind by viewBinding(ScreenAchievementsBinding::bind)
    private val adapterAchivements = AchivementsAdapter()

    private val list = ArrayList<StatisticData>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)

        loadAchivementsLoad()
        back.setOnClickListener { findNavController().popBackStack() }

        achivementsAdapter.adapter = adapterAchivements
        achivementsAdapter.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        adapterAchivements.submitList(list)
    }

    private fun loadAchivementsLoad() {
        list.add(StatisticData(50, R.drawable.ic_game, "Game"))
        list.add(StatisticData(44, R.drawable.ic_lenguage, "Language"))
        list.add(StatisticData(60, R.drawable.ic_math, "Math"))
        list.add(StatisticData(90, R.drawable.ic_sport, "Sport"))
        list.add(StatisticData(29, R.drawable.ic_history, "History"))
        list.add(StatisticData(66, R.drawable.ic_chemical, "Chemical"))
        list.add(StatisticData(77, R.drawable.ic_film, "Film"))
        list.add(StatisticData(49, R.drawable.ic_comdey, "Comedy"))
    }
}