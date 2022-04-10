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
import uz.usoft.quizapp.data.others.AnswerPassedData
import uz.usoft.quizapp.data.others.StaticValues
import uz.usoft.quizapp.data.response.map.MapLevelsResponse
import uz.usoft.quizapp.data.roomdata.entity.AnswerData
import uz.usoft.quizapp.data.roomdata.entity.PhotoData
import uz.usoft.quizapp.data.roomdata.entity.QuestionData
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers
import java.lang.StringBuilder
import javax.inject.Inject

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
        if (responce.isSuccessful) {
//            loadPassedQuestions()
            val list = loadStateCategory(
                responce.body()!!.createQuestionData() as ArrayList<QuestionAnswers>
            )
//            list.forEach {
//                dao.insertQuestionData(it.questionData)
//                dao.insertPhotos(it.photos)
//                dao.insertAnswerData(it.answers)
//            }
            emit(Result.success<List<QuestionAnswers>>(list))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch { exc ->
        val errorMessage = Throwable(this.toString())
        emit(Result.failure(exception = exc))
    }.flowOn(Dispatchers.IO)

    private fun loadPassedQuestions() {
        if (pref.passedAnswers.isEmpty()) {
            return
        }
        val value = pref.passedAnswers.split("#")
        val list = ArrayList<AnswerPassedData>()
        for (i in value.indices) {
            val data = value[i].split("$")
            list.add(AnswerPassedData(data[0].toInt(), data[1].toBoolean()))
        }
    }
//    override fun getLevelForBase(): Flow<Result<List<QuestionAnswers>>> = flow {
//        emit(Result.success<List<QuestionAnswers>>(dao.getAll()))
//    }.catch {
//        val errorMessage = Throwable(this.toString())
//        emit(Result.failure(errorMessage))
//    }.flowOn(Dispatchers.Unconfined)


    override fun getPlay(): Flow<Result<List<QuestionAnswers>>> = flow {
        val responce = api.getLevel(pref.level.toString())
        if (responce.isSuccessful) {
            loadPassedQuestions()
            val list = loadStateCategory(
                responce.body()!!.createQuestionData() as ArrayList<QuestionAnswers>
            )
            emit(Result.success<List<QuestionAnswers>>(list))
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

                mapLevelsResponse = responce.body()!!
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

    override fun setPassed(data: AnswerPassedData) {
        val passedData = pref.passedAnswers
        val stringBuilder = StringBuilder()
        stringBuilder.append(passedData).append(data.id).append("$").append(data.correct)
            .append("#")
        pref.passedAnswers = stringBuilder.toString()
        StaticValues.counter.add(data)
    }


    override fun getPassed(): Flow<ArrayList<AnswerPassedData>> = flow {
        val array = pref.passedAnswers.split("#")
        if (array.isNotEmpty()) {

            val list = ArrayList<AnswerPassedData>()
            for (i in array.indices) {
                val data = array[i].split("$")
               if(data.size==2)
               {
                   if (data[1] == "true") {
                       list.add(AnswerPassedData(data[0].toInt(), true))
                   } else {
                       list.add(AnswerPassedData(data[0].toInt(), false))
                   }
               }
            }
            emit(list)
        }
    }.flowOn(Dispatchers.IO)


    private fun loadStateCategory(response: ArrayList<QuestionAnswers>): ArrayList<QuestionAnswers> {
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
//        stateList.add(arrayState1)
//        stateList.add(arrayState2)
//        stateList.add(arrayState3)
//        stateList.add(arrayState4)
        stateList.add(arrayState5)
//        val myRandomValues = Random.nextInt(0, 4)

        var count = 0
        val list = ArrayList<QuestionAnswers>()
        for (i in stateList.get(0).indices) {
            if (stateList.get(0)[i] == 1 && count < response.size) {
                val value = response.get(count)
                list.add(QuestionAnswers(i, value.questionData, value.answers, value.photos, 1))
                count++
            } else {
//                list.add(
//                    QuestionAnswers(
//                        -1,
//                        getQuestionData(),
//                        emptyList<AnswerData>(),
//                        emptyList<PhotoData>(), 0
//                    )
//                )
                list.add(
                    QuestionAnswers(
                        i,
                        getQuestionData(),
                        emptyList(),
                        emptyList(), 0
                    )
                )

            }
        }
        return list
    }

    private fun getQuestionData(): QuestionData {
        return QuestionData(
            0,
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            0
        )
    }


}