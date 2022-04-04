package uz.usoft.quizapp.data.response.category

import uz.usoft.quizapp.data.roomdata.entity.QuestionTitleData

data class Title(
    val en: String,
    val ru: String,
    val uz: String
) {
    fun createQuestionTitleData(): QuestionTitleData {
        return QuestionTitleData(en, ru, uz)
    }
}