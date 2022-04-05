package uz.usoft.quizapp.data.domain.repositoryimpl

import uz.usoft.quizapp.data.pref.MyPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.usoft.quizapp.data.api.QuestionsApi
import uz.usoft.quizapp.data.base.TaskDao
import uz.usoft.quizapp.data.domain.repository.QuestionsRepository
import uz.usoft.quizapp.data.response.category.CategoryResponse
import uz.usoft.quizapp.data.response.category.Data
import uz.usoft.quizapp.data.response.map.MapLevelsResponse
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers
import javax.inject.Inject
import kotlin.random.Random

class QuestionsRepositoryImpl @Inject constructor(
    private val api: QuestionsApi,
    private val pref: MyPref,
    private val dao: TaskDao
) :
    QuestionsRepository {
    private var mapLevelsResponse: MapLevelsResponse? = null


    override fun getLevel(id: String): Flow<Result<List<QuestionAnswers>>> = flow {
        pref.level = id.toInt()
        val responce = api.getLevel(id)
        dao.deleteTask()
        if (responce.isSuccessful) {
            val list = responce.body()!!.createQuestionData()
            list.forEach {
                dao.insertQuestionData(it.questionData)
                dao.insertPhotos(it.photos)
                dao.insertAnswerData(it.answers)
            }
            emit(Result.success<List<QuestionAnswers>>(dao.getAll()))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable(this.toString())
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

//    override fun getLevelForBase(): Flow<Result<List<QuestionAnswers>>> = flow {
//        emit(Result.success<List<QuestionAnswers>>(dao.getAll()))
//    }.catch {
//        val errorMessage = Throwable(this.toString())
//        emit(Result.failure(errorMessage))
//    }.flowOn(Dispatchers.Unconfined)


    override fun getPlay(): Flow<Result<List<QuestionAnswers>>> = flow {
        val responce = api.getLevel(pref.level.toString())
        if (responce.isSuccessful) {
            emit(Result.success<List<QuestionAnswers>>(dao.getAll()))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable(this.toString())
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun getLevelQuestions(): Flow<Result<MapLevelsResponse>> = flow {
        if (mapLevelsResponse == null) {
            val responce = api.getLevelsQuestions()
            if (responce.isSuccessful) {
                emit(Result.success<MapLevelsResponse>(responce.body()!!))
            } else {
                emit(Result.failure(Throwable(responce.errorBody().toString())))
            }
        } else {
            emit(Result.success<MapLevelsResponse>(mapLevelsResponse!!))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

//    override fun setPassed(): Flow<Unit> = flow {
//
//        emit(Result.success<Unit>(responce.body()!!))
//
//    }.catch {
//        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
//        emit(Result.failure(errorMessage))
//    }.flowOn(Dispatchers.IO)
//    private fun savePassed(id:)
//    override fun getPassed(): Flow<Result<String>> = flow {
//        emit(Result.success<String>(mapLevelsResponse!!))
//    }.catch {
//        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
////        emit(Result.failure(errorMessage))
//    }.flowOn(Dispatchers.IO)


//    fun reformatResponse(array: CategoryResponse): CategoryResponse {
//        var count = 5
//        var helper = 0
//        var counter = 5
//        val list = ArrayList<Data>()
//        for (i in 0 until array.data.size * 2) {
//            if (count == 0) {
//                count = 5
//                counter--
//            }
//            if (counter > 0) {
//                val value = array.data[helper]
//                list.add(getValue(value))
//                helper++
//            } else {
//                list.add(getValueNull())
//            }
//            count--
//        }
//        return CategoryResponse(list)
//    }

//    private fun getValueNull(): Data {
//        return Data(emptyList(), )
//    }

    private fun loadStateCategory(response: CategoryResponse): CategoryResponse {
        val arrayState1 = arrayOf(
            0, 1, 1, 1, 0,
            0, 1, 0, 1, 0,
            1, 1, 1, 1, 1,
            1, 0, 0, 0, 1,
            1, 1, 1, 1, 1,
            0, 1, 0, 1, 0,
            0, 1, 1, 1, 0
        )
        val arrayState2 = arrayOf(
            1, 1, 1, 1, 1,
            0, 0, 0, 0, 1,
            0, 0, 0, 0, 1,
            1, 1, 1, 1, 1,
            1, 0, 0, 0, 0,
            1, 0, 0, 0, 1,
            1, 1, 1, 1, 1
        )

        val arrayState3 = arrayOf(
            1, 0, 0, 0, 1,
            0, 1, 0, 1, 0,
            0, 0, 1, 0, 0,
            0, 1, 1, 1, 0,
            1, 0, 1, 0, 1,
            1, 0, 1, 0, 1,
            1, 1, 1, 1, 1,
            0, 0, 1, 0, 0,
        )
        val arrayState4 = arrayOf(
            1, 1, 1, 1, 1,
            1, 0, 0, 0, 1,
            1, 0, 0, 0, 1,
            1, 0, 0, 0, 1,
            1, 0, 0, 0, 1,
            1, 0, 0, 0, 1,
            1, 1, 1, 1, 1,

            )
        val arrayState5 = arrayOf(
            1, 1, 0, 1, 1,
            0, 1, 0, 1, 0,
            0, 1, 1, 1, 0,
            0, 1, 0, 1, 0,
            0, 1, 1, 1, 0,
            0, 1, 0, 1, 0,
            1, 1, 0, 1, 1
        )
        val stateList = ArrayList<Array<Int>>()
        stateList.add(arrayState1)
        stateList.add(arrayState2)
        stateList.add(arrayState3)
        stateList.add(arrayState4)
        stateList.add(arrayState5)
        val myRandomValues = Random.nextInt(0, 4)

        var count = 0
        val list = ArrayList<Data>()
        for (i in stateList.get(myRandomValues).indices) {
            if (stateList.get(myRandomValues)[i] == 1 && count < response.data.size) {
                val value = response.data.get(count)
                list.add(
                    Data(
                        value.answers,
                        value.category,
                        value.description,
                        value.id,
                        value.photos,
                        value.title,
                        1
                    )
                )
                count++
            } else {
//                list.add(Data(emptyList(), null, null, null, null, null, 0))
            }
        }
        val levelResponse = CategoryResponse(list)
        return levelResponse
    }

//    private fun networkToBase(response: CategoryResponse): CategoryData {
//        return CategoryData(response)
//    }


}


/**




 * */