package com.sirem.tesaruna.di

import android.app.Application
import androidx.room.Room
import com.sirem.tesaruna.model.DataRepository
import com.sirem.tesaruna.room.AppDatabase
import com.sirem.tesaruna.room.DAO
import com.sirem.tesaruna.utils.RestApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


//Provide all the app level dependency here
@Module
class AppModule {


    // Method #1
    @Singleton
    @Provides
    fun providesAppDatabase(app:Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java,"post_database").build()
    }

    // Method #2
    @Singleton
    @Provides
    fun providesNoteDao(db: AppDatabase): DAO {
        return db.DAO()
    }

    // Method #3
    @Singleton
    @Provides
    fun providesRepository(noteDao: DAO, service: RestApi):DataRepository{
        return DataRepository(noteDao,service)
    }
}