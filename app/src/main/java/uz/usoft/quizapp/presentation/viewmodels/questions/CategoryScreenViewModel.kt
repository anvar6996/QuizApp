package uz.usoft.quizapp.presentation.viewmodels.questions

import kotlinx.coroutines.flow.Flow
import uz.usoft.quizapp.data.others.AnswerPassedData
import uz.usoft.quizapp.data.response.category.CategoryResponse
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers

interface CategoryScreenViewModel {
    val errorFlow: Flow<String>
    val successFlow: Flow<List<QuestionAnswers>>
    val successPassedFlow: Flow<ArrayList<AnswerPassedData>>
    val progressFlow: Flow<Boolean>

    fun getQuestions(id: String)
    fun getPlay()
    fun getPassedData()
}