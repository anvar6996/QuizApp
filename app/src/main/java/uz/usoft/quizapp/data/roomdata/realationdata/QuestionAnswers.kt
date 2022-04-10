package uz.usoft.quizapp.data.roomdata.realationdata

import androidx.room.Embedded
import androidx.room.Relation
import uz.usoft.quizapp.data.roomdata.entity.*

data class QuestionAnswers(
    val position: Int,
    @Embedded
    val questionData: QuestionData,

    @Relation(
        parentColumn = "questionDataId",
        entityColumn = "parentQuestionId"
    )
    @Embedded
    val answers: List<AnswerData>,

    @Relation(
        parentColumn = "questionDataId",
        entityColumn = "photoParentId"
    )
    @Embedded
    val photos: List<PhotoData>,
    val state: Int = 1
)
