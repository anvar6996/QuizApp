package uz.usoft.quizapp.data.roomdata.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import uz.usoft.quizapp.data.response.category.Name

@Entity(tableName = "category_res_data")
data class CategoryResData(
    @PrimaryKey
    val categoryResDataId: Int,
    @Embedded
    val categoryNameData: CategoryNameData,
    val photo: String
)