package uz.usoft.quizapp.data.response.category

import uz.usoft.quizapp.data.roomdata.entity.QuestionData
import uz.usoft.quizapp.data.roomdata.entity.QuestionDesciprionData

data class Description(
    val en: String,
    val ru: String,
    val uz: String
) {
    fun reformatToQuestionDate(): QuestionDesciprionData {
        return QuestionDesciprionData(
            en, ru, uz)
    }
}