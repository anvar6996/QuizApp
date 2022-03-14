package uz.usoft.quizapp.presentation.viewmodels.questions

import kotlinx.coroutines.flow.Flow
import uz.usoft.quizapp.data.response.map.MapLevelsResponse

interface MapLevelsScreenViewModel {
    val errorFlow: Flow<String>
    val successFlow: Flow<MapLevelsResponse>
    val progressFlow: Flow<Boolean>

    fun getMapLevels()
}