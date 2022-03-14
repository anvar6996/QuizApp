package uz.usoft.quizapp.data.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.usoft.quizapp.data.response.level.LevelResponse
import uz.usoft.quizapp.data.response.map.MapLevelsResponse

interface QuestionsRepository {

    fun getLevel(id:String): Flow<Result<LevelResponse>>
    fun getLevelQuestions(): Flow<Result<MapLevelsResponse>>
}