package uz.usoft.quizapp.data.response.category

import uz.usoft.quizapp.data.roomdata.entity.AnswerData

data class Answer(
    val answer: AnswerX,
    val correct: Boolean,
    val id: Int
) {
    fun reformatToAnswerData(): AnswerData {
        return AnswerData(id,answer.reformatToAnswerTranslateDate(), correct)
    }
}