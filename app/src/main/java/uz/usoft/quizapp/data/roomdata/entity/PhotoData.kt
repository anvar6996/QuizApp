package uz.usoft.quizapp.data.roomdata.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_data")
data class PhotoData(
    val path: String,
    val type: String,
    @PrimaryKey
    val photoDataId: Int,
    val photoParentId: Int=0
)