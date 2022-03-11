package uz.usoft.quizapp.data.response.level

data class LevelResponseItem(
    val answers: List<Answer>,
    val category: Category,
    val description: Description,
    val id: Int,
    val images: List<Image>,
    val title: Title
)