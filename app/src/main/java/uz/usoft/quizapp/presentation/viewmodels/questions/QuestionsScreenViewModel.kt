package uz.usoft.quizapp.presentation.viewmodels.questions

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import uz.usoft.quizapp.data.response.category.CategoryResponse
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers


interface QuestionsScreenViewModel {
    val errorFlow: Flow<String>
    val successFlow: Flow<CategoryResponse>
    val progressFlow: Flow<Boolean>
    val liveDataScreenClose: MutableLiveData<Unit>

    fun getQuestions(id: String)
    fun screenClose(id: String)
}