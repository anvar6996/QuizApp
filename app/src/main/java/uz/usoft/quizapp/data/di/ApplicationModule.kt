package uz.usoft.quizapp.data.di

import android.content.Context
import uz.usoft.quizapp.app.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun getAppContext(): Context = App.instance

}