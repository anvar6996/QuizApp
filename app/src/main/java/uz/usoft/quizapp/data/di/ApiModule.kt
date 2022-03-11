package uz.usoft.quizapp.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.usoft.quizapp.data.api.QuestionsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @[Provides Singleton]
    fun getAuthApi(retrofit: Retrofit): QuestionsApi = retrofit.create(QuestionsApi::class.java)

}