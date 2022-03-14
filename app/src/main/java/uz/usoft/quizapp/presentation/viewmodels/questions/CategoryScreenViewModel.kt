package uz.usoft.quizapp.presentation.viewmodels.questions

import kotlinx.coroutines.flow.Flow
import uz.usoft.quizapp.data.response.level.LevelResponse

interface CategoryScreenViewModel {
    val errorFlow: Flow<String>
    val successFlow: Flow<LevelResponse>
    val progressFlow: Flow<Boolean>

    fun getQuestions(id:String)
}