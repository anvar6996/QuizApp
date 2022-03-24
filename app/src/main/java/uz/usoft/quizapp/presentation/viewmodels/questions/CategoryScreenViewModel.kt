package uz.usoft.quizapp.presentation.viewmodels.questions

import kotlinx.coroutines.flow.Flow
import uz.usoft.quizapp.data.response.category.CategoryResponse

interface CategoryScreenViewModel {
    val errorFlow: Flow<String>
    val successFlow: Flow<CategoryResponse>
    val progressFlow: Flow<Boolean>

    fun getQuestions(id:String)
}