package uz.usoft.quizapp.data.api

import retrofit2.Response
import retrofit2.http.*
import uz.usoft.quizapp.data.response.category.CategoryResponse
import uz.usoft.quizapp.data.response.map.MapLevelsResponse

interface QuestionsApi {

    @GET("levels/{id}")
    suspend fun getLevel(@Path("id") id: String): Response<CategoryResponse>

    @GET("levels")
    suspend fun getLevelsQuestions(): Response<MapLevelsResponse>


//    @GET("auth/me")
//    suspend fun getMeInfo(): Response<ProfileResponce>

}