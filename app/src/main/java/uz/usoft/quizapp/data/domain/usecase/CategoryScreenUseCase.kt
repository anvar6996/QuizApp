package uz.usoft.quizapp.data.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.usoft.quizapp.data.others.AnswerPassedData
import uz.usoft.quizapp.data.response.category.CategoryResponse
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers

interface CategoryScreenUseCase {
    fun getLevel(id: String): Flow<Result<List<QuestionAnswers>>>
    fun getPlay(): Flow<Result<List<QuestionAnswers>>>
    fun getPassedData(): Flow<ArrayList<AnswerPassedData>>
}