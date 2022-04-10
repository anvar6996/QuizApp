package uz.usoft.quizapp.data.base

import androidx.room.*
import uz.usoft.quizapp.data.roomdata.entity.AnswerData
import uz.usoft.quizapp.data.roomdata.entity.PhotoData
import uz.usoft.quizapp.data.roomdata.entity.QuestionData
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers


@Dao
interface TaskDao {

//    @Transaction
//    @Query("SELECT * FROM questions")
//    fun getAll(): List<QuestionAnswers>
//
//    @Query("DELETE FROM questions")
//    fun deleteTask(): Int
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertQuestionData(data: QuestionData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPhotos(data: List<PhotoData>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAnswerData(data: List<AnswerData>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun insertForEdit(data: QuestionData)

    @Update
    fun update(data: QuestionData)
}