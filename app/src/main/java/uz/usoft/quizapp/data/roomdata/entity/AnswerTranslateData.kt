package uz.usoft.quizapp.data.roomdata.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dagger.hilt.android.AndroidEntryPoint

@Entity(tableName = "translate_answer")
data class AnswerTranslateData(

    val aEn: String,
    val aRu: String,
    val aUz: String,
    @PrimaryKey(autoGenerate = true)
    val answerTranslateData: Int=0
)