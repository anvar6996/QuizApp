package uz.usoft.quizapp.data.roomdata.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("name_category")
data class CategoryNameData(

    val aEn: String,
    val aUz: String,
    val aRu: String,
    @PrimaryKey(autoGenerate = true)
    val categoryNameData: Int=0
)