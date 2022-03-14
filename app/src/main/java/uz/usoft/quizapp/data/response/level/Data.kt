package uz.usoft.quizapp.data.response.level

data class Data(
    val answers: List<Answer>,
    val category: Category,
    val description: Description,
    val id: Int,
    val photos: List<Photo>,
    val title: Title
)