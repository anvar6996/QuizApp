package uz.usoft.quizapp.data.response.category

import uz.usoft.quizapp.data.roomdata.entity.AnswerData

import uz.usoft.quizapp.data.roomdata.entity.PhotoData
import uz.usoft.quizapp.data.roomdata.entity.QuestionData
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers
import java.io.Serializable
import kotlin.random.Random

data class Data(
    val answers: List<Answer>,
    val category: Category,
    val description: Description,
    val id: Int?,
    val photos: List<Photo>,
    val title: Title,
) : Serializable {


    fun reformatToQuestionData(position: Int): QuestionAnswers {
        return QuestionAnswers(
            position,
            QuestionData(
                id!!,
                category.photo,
                category.name.en,
                category.name.uz,
                category.name.ru,
                description.en,
                description.uz,
                description.ru,
                title.en,
                title.uz,
                title.ru
            ),
            createListAnswers(),
            createListPhotos(),
            1
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

    private fun loadStateCategory(response: CategoryResponse): CategoryResponse {
        val arrayState1 = arrayOf(
            0, 1, 1, 1, 0,
            0, 1, 0, 1, 0,
            1, 1, 1, 1, 1,
            1, 0, 0, 0, 1,
            1, 1, 1, 1, 1,
            0, 1, 0, 1, 0,
            0, 1, 1, 1, 0
        )
        val arrayState2 = arrayOf(
            1, 1, 1, 1, 1,
            0, 0, 0, 0, 1,
            0, 0, 0, 0, 1,
            1, 1, 1, 1, 1,
            1, 0, 0, 0, 0,
            1, 0, 0, 0, 1,
            1, 1, 1, 1, 1
        )

        val arrayState3 = arrayOf(
            1, 0, 0, 0, 1,
            0, 1, 0, 1, 0,
            0, 0, 1, 0, 0,
            0, 1, 1, 1, 0,
            1, 0, 1, 0, 1,
            1, 0, 1, 0, 1,
            1, 1, 1, 1, 1,
            0, 0, 1, 0, 0,
        )
        val arrayState4 = arrayOf(
            1, 1, 1, 1, 1,
            1, 0, 0, 0, 1,
            1, 0, 0, 0, 1,
            1, 0, 0, 0, 1,
            1, 0, 0, 0, 1,
            1, 0, 0, 0, 1,
            1, 1, 1, 1, 1,

            )
        val arrayState5 = arrayOf(
            1, 1, 0, 1, 1,
            0, 1, 0, 1, 0,
            0, 1, 1, 1, 0,
            0, 1, 0, 1, 0,
            0, 1, 1, 1, 0,
            0, 1, 0, 1, 0,
            1, 1, 0, 1, 1
        )
        val stateList = ArrayList<Array<Int>>()
        stateList.add(arrayState1)
        stateList.add(arrayState2)
        stateList.add(arrayState3)
        stateList.add(arrayState4)
        stateList.add(arrayState5)
        val myRandomValues = Random.nextInt(0, 4)

        var count = 0
        val list = ArrayList<Data>()
        for (i in stateList.get(myRandomValues).indices) {
            if (stateList.get(myRandomValues)[i] == 1 && count < response.data.size) {
                val value = response.data.get(count)
                list.add(
                    Data(
                        value.answers,
                        value.category,
                        value.description,
                        value.id,
                        value.photos,
                        value.title,
                    )
                )
                count++
            } else {
//                list.add(Data(emptyList(), null, null, null, null, null, 0))
            }
        }
        val levelResponse = CategoryResponse(list)
        return levelResponse
    }
}