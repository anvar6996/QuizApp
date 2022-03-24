package uz.usoft.quizapp.data.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.usoft.quizapp.data.response.category.CategoryResponse
import uz.usoft.quizapp.data.response.map.MapLevelsResponse

interface QuestionsRepository {

    fun getLevel(id:String): Flow<Result<CategoryResponse>>
    fun getLevelQuestions(): Flow<Result<MapLevelsResponse>>
}