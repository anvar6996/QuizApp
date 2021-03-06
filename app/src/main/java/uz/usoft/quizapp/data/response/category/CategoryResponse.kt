package uz.usoft.quizapp.data.response.category

import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers
import kotlin.random.Random

data class CategoryResponse(
    val `data`: List<Data>
) {

    fun createQuestionData(): List<QuestionAnswers> {
        val list = ArrayList<QuestionAnswers>()
        for (i in data.indices) {
            list.add(data[i].reformatToQuestionData(i))
        }
        return list
    }


}