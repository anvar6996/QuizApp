package uz.usoft.quizapp.data.roomdata.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_desciption_data")
class QuestionDesciprionData(
    val qEn: String,
    val qRu: String,
    val qUz: String,
    @PrimaryKey(autoGenerate = true)
    val questionDesciprionData: Int=0
)