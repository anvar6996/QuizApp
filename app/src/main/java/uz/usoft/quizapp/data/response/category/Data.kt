package uz.usoft.quizapp.data.response.category

import uz.usoft.quizapp.data.roomdata.entity.AnswerData

import uz.usoft.quizapp.data.roomdata.entity.PhotoData
import uz.usoft.quizapp.data.roomdata.entity.QuestionData
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers
import java.io.Serializable

data class Data(
    val answers: List<Answer>,
    val category: Category?,
    val description: Description?,
    val id: Int?,
    val photos: List<Photo>?,
    val title: Title?,
    val stateShow: Int = 0
) : Serializable {

    fun reformatToQuestionData(): QuestionAnswers {
        return QuestionAnswers(
            QuestionData(
                id!!,
                category!!.reformatToCategoryDate(),
                description!!.reformatToQuestionDate(),
                title!!.createQuestionTitleData(),
                stateShow
            ),
            createListAnswers(),
            createListPhotos(), id, id
        )
    }

    private fun createListAnswers(): ArrayList<AnswerData> {
        val list = ArrayList<AnswerData>()
        for (i in answers.indices) {
            list.add(answers[i].reformatToAnswerData())
        }
        return list
    }

    private fun createListPhotos(): ArrayList<PhotoData> {
        val list = ArrayList<PhotoData>()
        for (i in photos!!.indices) {
            list.add(photos[i].reformatToPhotoData())
        }
        return list
    }
}