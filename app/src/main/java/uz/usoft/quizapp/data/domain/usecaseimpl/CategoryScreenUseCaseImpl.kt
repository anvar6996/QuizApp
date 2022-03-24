package uz.usoft.quizapp.data.domain.usecaseimpl

import kotlinx.coroutines.flow.Flow
import uz.usoft.quizapp.data.domain.repository.QuestionsRepository
import uz.usoft.quizapp.data.domain.usecase.CategoryScreenUseCase
import uz.usoft.quizapp.data.response.category.CategoryResponse
import uz.usoft.quizapp.data.response.map.MapLevelsResponse
import javax.inject.Inject

class CategoryScreenUseCaseImpl @Inject constructor(private val repository: QuestionsRepository) :
    CategoryScreenUseCase {

    override fun getLevel(id: String): Flow<Result<CategoryResponse>> {
        return repository.getLevel(id)
    }
}