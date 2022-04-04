package uz.usoft.quizapp.data.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.usoft.quizapp.data.base.AppBase
import uz.usoft.quizapp.data.base.TaskDao
import uz.usoft.quizapp.app.App

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun getDataBase(): AppBase {
        return Room.databaseBuilder(App.instance, AppBase::class.java, "Quiz App").build()
    }

    @Provides
    fun getTaskDao(base: AppBase): TaskDao {
        return base.getDao()
    }
}