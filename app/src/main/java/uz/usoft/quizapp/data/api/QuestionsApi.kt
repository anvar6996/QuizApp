package uz.usoft.quizapp.data.api

import retrofit2.Response
import retrofit2.http.*
import uz.usoft.quizapp.data.response.level.LevelResponse
import uz.usoft.quizapp.data.response.map.MapLevelsResponse

interface QuestionsApi {

    @GET("levels/1")
    suspend fun getLevel(): Response<LevelResponse>

    @GET("/logout")
    suspend fun getQuestions(): Response<MapLevelsResponse>


//    @GET("auth/me")
//    suspend fun getMeInfo(): Response<ProfileResponce>

}