package uz.usoft.quizapp.data.roomdata.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "questions_title_data")
data class QuestionTitleData(
    val tEn: String,
    val tUz: String,
    val tRu: String,
    @PrimaryKey(autoGenerate = true)
    val questionTitleData: Int=0
)