package uz.usoft.quizapp.data.domain.repositoryimpl

import com.example.express24task.data.pref.MyPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.usoft.quizapp.data.api.QuestionsApi
import uz.usoft.quizapp.data.domain.repository.QuestionsRepository
import uz.usoft.quizapp.data.response.category.CategoryResponse
import uz.usoft.quizapp.data.response.category.Data
import uz.usoft.quizapp.data.response.map.MapLevelsResponse
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val api: QuestionsApi,
    private val pref: MyPref
) :
    QuestionsRepository {
    private var categoryResponse: CategoryResponse? = null


    override fun getLevel(id: String): Flow<Result<CategoryResponse>> = flow {
        val responce = api.getLevel(id)

        if (responce.isSuccessful) {
            categoryResponse=responce.body()!!
            emit(Result.success<CategoryResponse>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable(this.toString())
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.Unconfined)

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


    /**
    stateList.add(intArrayOf(0, 1, 1, 1, 0).toCollection(ArrayList()))
    stateList.add(intArrayOf(0, 1, 0, 1, 0).toCollection(ArrayList()))
    stateList.add(intArrayOf(1, 1, 1, 1, 1).toCollection(ArrayList()))
    stateList.add(intArrayOf(1, 0, 0, 0, 1).toCollection(ArrayList()))
    stateList.add(intArrayOf(1, 1, 1, 1, 1).toCollection(ArrayList()))
    stateList.add(intArrayOf(0, 1, 0, 1, 0).toCollection(ArrayList()))
    stateList.add(intArrayOf(0, 1, 1, 1, 0).toCollection(ArrayList()))
     * */


    fun reformatResponse(array: CategoryResponse): CategoryResponse {
        var count = 5
        var helper = 0
        var counter = 5
        val list = ArrayList<Data>()
        for (i in 0 until array.data.size * 2) {
            if (count == 0) {
                count = 5
                counter--
            }
            if (counter > 0) {
                val value = array.data[helper]
                list.add(getValue(value))
                helper++
            } else {
                list.add(getValueNull())
            }
            count--
        }
        return CategoryResponse(list)
    }

    private fun getValue(data: Data): Data {
        return Data(
            data.answers,
            data.category,
            data.description,
            data.id,
            data.photos,
            data.title,
            1
        )
    }

    private fun getValueNull(): Data {
        return Data(emptyList(), null, null, null, null, null, 0)
    }
}

/**

private fun loadStateCategory(response: LevelResponse): LevelResponse {
val arrayState = arrayOf(
0, 1, 1, 1, 0,
0, 1, 0, 1, 0,
1, 1, 1, 1, 1,
1, 0, 0, 0, 1,
1, 1, 1, 1, 1,
0, 1, 0, 1, 0,
0, 1, 1, 1, 0
)
var count = 0
val list = ArrayList<Data>()
for (i in arrayState.indices) {
if (arrayState[i] == 0) {
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
list.add(Data(emptyList(), null, null, null, null, null, 0))
}
}
val levelResponse = LevelResponse(list)
return levelResponse
}
 * */