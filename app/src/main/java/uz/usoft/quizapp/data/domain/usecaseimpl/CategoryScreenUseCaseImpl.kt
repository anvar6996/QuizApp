package uz.usoft.quizapp.data.domain.usecaseimpl

import kotlinx.coroutines.flow.Flow
import uz.usoft.quizapp.data.domain.repository.QuestionsRepository
import uz.usoft.quizapp.data.domain.usecase.CategoryScreenUseCase
import uz.usoft.quizapp.data.pref.MyPref
import uz.usoft.quizapp.data.response.category.CategoryResponse
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers
import javax.inject.Inject

class CategoryScreenUseCaseImpl @Inject constructor(
    private val repository: QuestionsRepository,
    private val pref: MyPref
) :
    CategoryScreenUseCase {

    override fun getLevel(id: String): Flow<Result<CategoryResponse>> {
//        if (pref.level == id.toInt()) {
//            return repository.getLevelForBase()
//        } else {
        return repository.getLevel(id)
//        }
    }

    override fun getPlay(): Flow<Result<CategoryResponse>> = repository.getPlay()
}