package uz.usoft.quizapp.data.response.category

import uz.usoft.quizapp.data.roomdata.entity.PhotoData
import uz.usoft.quizapp.data.roomdata.entity.QuestionDesciprionData

data class Photo(
    val id: Int,
    val path: String,
    val type: String
) {
    fun reformatToPhotoData(): PhotoData {
        return PhotoData(
            path, type, id
        )
    }
}