package uz.usoft.quizapp.data.others

import dagger.Provides
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers

object StaticValues {
    lateinit var questionAnswers: QuestionAnswers
    var controllerLoad: Boolean = true
    var counter = ArrayList<AnswerPassedData>()
}