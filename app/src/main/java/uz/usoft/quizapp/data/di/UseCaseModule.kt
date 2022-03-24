package uz.usoft.quizapp.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import uz.usoft.quizapp.data.domain.repository.QuestionsRepository
import uz.usoft.quizapp.data.domain.repositoryimpl.QuestionsRepositoryImpl
import uz.usoft.quizapp.data.domain.usecase.CategoryScreenUseCase
import uz.usoft.quizapp.data.domain.usecaseimpl.CategoryScreenUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun getCategoryScreenUseCase(impl:  CategoryScreenUseCaseImpl): CategoryScreenUseCase

}