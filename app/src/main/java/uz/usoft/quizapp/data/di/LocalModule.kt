package uz.usoft.quizapp.data.di

import android.content.Context
import com.example.express24task.data.pref.MyPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @[Provides Singleton]
    fun getSharedPreferences(@ApplicationContext context: Context): MyPref = MyPref(context)

}