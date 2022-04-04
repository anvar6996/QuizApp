package uz.usoft.quizapp.data.roomdata.entity

import androidx.room.*

@Entity(tableName = "questions")
data class QuestionData(
    @PrimaryKey
    val questionDataId: Int,
    @Embedded("category_")
    val category: CategoryResData,
    @Embedded("desciption_")
    val description: QuestionDesciprionData,
    @Embedded("title_")
    val title: QuestionTitleData,
    val stateShow: Int = 0
)
