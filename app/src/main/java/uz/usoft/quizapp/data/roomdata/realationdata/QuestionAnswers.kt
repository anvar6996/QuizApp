package uz.usoft.quizapp.data.roomdata.realationdata

import androidx.room.Embedded
import androidx.room.Relation
import uz.usoft.quizapp.data.roomdata.entity.*

data class QuestionAnswers(
    @Embedded
    val questionData: QuestionData,

    @Relation(
        parentColumn = "questionDataId",
        entityColumn = "parentQuestionId"
    )
    val answers: List<AnswerData>,

    @Relation(
        parentColumn = "questionDataId",
        entityColumn = "photoParentId"
    )
    val photos: List<PhotoData>
)
