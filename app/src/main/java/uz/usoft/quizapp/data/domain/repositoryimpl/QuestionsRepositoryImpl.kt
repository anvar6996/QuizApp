package uz.usoft.quizapp.data.domain.repositoryimpl

import com.example.express24task.data.pref.MyPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.usoft.quizapp.data.api.QuestionsApi
import uz.usoft.quizapp.data.domain.repository.QuestionsRepository
import uz.usoft.quizapp.data.response.level.LevelResponse
import uz.usoft.quizapp.data.response.map.MapLevelsResponse
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(private val api: QuestionsApi, private val pref: MyPref) :
    QuestionsRepository {

    override fun getLevel(id:String): Flow<Result<LevelResponse>> = flow {
        val responce = api.getLevel(id)
        if (responce.isSuccessful) {
            emit(Result.success<LevelResponse>(responce.body()!!))
        }
        else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable(this.toString())
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun getLevelQuestions(): Flow<Result<MapLevelsResponse>> = flow {
        val responce = api.getLevelsQuestions()
        if (responce.isSuccessful) {
            emit(Result.success<MapLevelsResponse>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {

        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)


}