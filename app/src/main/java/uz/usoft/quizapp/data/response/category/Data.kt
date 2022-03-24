package uz.usoft.quizapp.data.response.category

import java.io.Serializable

data class Data(
    val answers: List<Answer>,
    val category: Category?,
    val description: Description?,
    val id: Int?,
    val photos: List<Photo>?,
    val title: Title?,
    val stateShow: Int = 0
) : Serializable