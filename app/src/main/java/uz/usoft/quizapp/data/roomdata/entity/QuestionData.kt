package uz.usoft.quizapp.data.roomdata.entity

import androidx.room.*

@Entity(tableName = "questions")
data class QuestionData(
    @PrimaryKey
    val questionDataId: Int,
//    @Embedded("category_")
    val categoryPhoto: String,

//    val category: CategoryResData,
    val categoryResEn: String?,
    val categoryResUz: String?,
    val categoryResRu: String?,
//    @Embedded("desciption_")
//    val description: QuestionDesciprionData,
    val descriptionEn: String?,
    val descriptionRu: String?,
    val descriptionUz: String?,
//    @Embedded("title_")
//    val title: QuestionTitleData,
    val titleEn: String?,
    val titleUz: String?,
    val titleRu: String?,
    val stateShow: Int = 0
)
