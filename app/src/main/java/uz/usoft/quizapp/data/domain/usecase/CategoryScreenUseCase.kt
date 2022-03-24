package uz.usoft.quizapp.data.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.usoft.quizapp.data.response.category.CategoryResponse
import uz.usoft.quizapp.data.response.map.MapLevelsResponse

interface CategoryScreenUseCase {
    fun getLevel(id: String): Flow<Result<CategoryResponse>>
}