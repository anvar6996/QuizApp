package uz.usoft.quizapp.data.roomdata.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answer")
data class AnswerData(
    @PrimaryKey
    val answerDataId: Int = 0,
    @Embedded
    val answerTranslateData: AnswerTranslateData,
    val correct: Boolean,
//    val parentQuestionId: Int = 0
)