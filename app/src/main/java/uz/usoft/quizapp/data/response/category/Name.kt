package uz.usoft.quizapp.data.response.category

import uz.usoft.quizapp.data.roomdata.entity.CategoryNameData
import uz.usoft.quizapp.data.roomdata.entity.CategoryResData

data class Name(
    val en: String,
    val ru: String,
    val uz: String
) {
    fun reformatToCategoryDate(): CategoryNameData {
        return CategoryNameData(en, ru, uz)
    }
}