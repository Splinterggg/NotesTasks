package com.app.notestodos.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.app.notestodos.NoteDatabase
import com.app.notestodos.dao.NoteDao
import com.app.notestodos.entity.Note
import com.app.notestodos.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun providesRoomDatabase(app:Application): NoteDatabase{
        return Room.databaseBuilder(app,NoteDatabase::class.java,"notes_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Singleton
    @Provides
    fun providesRepository(noteDao: NoteDao): Repository {
        return Repository(noteDao)
    }
    @Singleton
    @Provides
    fun providesNoteDao(db:NoteDatabase):NoteDao{
        return db.noteDao()
    }

    @Singleton
    @Provides
    fun provideAllNotes(repository: Repository): LiveData<List<Note>>{
        return repository.getAllNotes()
    }
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

}
