package uz.usoft.quizapp.data.response.category

import uz.usoft.quizapp.data.roomdata.entity.CategoryResData

data class Category(
    val id: Int,
    val name: Name,
    val photo: String
) {
    fun reformatToCategoryDate(): CategoryResData {
        return CategoryResData(id, name.reformatToCategoryDate(), photo)
    }
}