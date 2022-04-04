package uz.usoft.quizapp.data.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.usoft.quizapp.data.response.category.CategoryResponse
import uz.usoft.quizapp.data.response.map.MapLevelsResponse
import uz.usoft.quizapp.data.roomdata.entity.QuestionData
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers

interface QuestionsRepository {

    fun getLevel(id: String): Flow<Result<CategoryResponse>>
    fun getPlay(): Flow<Result<CategoryResponse>>
    fun getLevelQuestions(): Flow<Result<MapLevelsResponse>>
//    fun setPassed(): Flow<Unit>
//    fun getPassed(): Flow<Result<String>>
}