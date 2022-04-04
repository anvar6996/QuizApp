package uz.usoft.quizapp.data.base

//import dagger.hilt.components.SingletonComponent
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.usoft.quizapp.data.roomdata.entity.*


@Database(
    entities =
    [

        QuestionData::class,
        AnswerData::class,
        CategoryNameData::class,
        CategoryResData::class,
        PhotoData::class,
        QuestionDesciprionData::class,
        QuestionTitleData::class,
    ], version = 4, exportSchema = true
)
//@TypeConverters(Converters::class)
abstract class AppBase() : RoomDatabase() {

    abstract fun getDao(): TaskDao

}