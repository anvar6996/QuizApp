package uz.usoft.quizapp.data.response.category

import uz.usoft.quizapp.data.roomdata.entity.AnswerTranslateData

data class AnswerX(
    val en: String,
    val ru: String,
    val uz: String
) {
    fun reformatToAnswerTranslateDate(): AnswerTranslateData {
        return AnswerTranslateData(en, ru, uz)
    }

}