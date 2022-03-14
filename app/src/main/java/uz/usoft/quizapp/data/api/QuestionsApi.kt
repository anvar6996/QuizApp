package uz.usoft.quizapp.data.api

import retrofit2.Response
import retrofit2.http.*
import uz.usoft.quizapp.data.response.level.LevelResponse
import uz.usoft.quizapp.data.response.map.MapLevelsResponse

interface QuestionsApi {

    @GET("levels/{id}")
    suspend fun getLevel(@Path("id") id: String): Response<LevelResponse>

    @GET("levels")
    suspend fun getLevelsQuestions(): Response<MapLevelsResponse>


//    @GET("auth/me")
//    suspend fun getMeInfo(): Response<ProfileResponce>

}