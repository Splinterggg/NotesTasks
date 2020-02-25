package com.app.notestodos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.app.notestodos.dao.NoteDao
import com.app.notestodos.entity.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {




        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            if (instance == null) {
                synchronized(NoteDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java, "notes_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance!!
        }
        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDb(instance)
            }
        }


        private fun PopulateDb(db: NoteDatabase?) {
            val noteDao = db?.noteDao()
            GlobalScope.launch {
                noteDao?.insert(Note("Title 1", "description 1"))
                noteDao?.insert(Note("Title 2", "description 2"))
                noteDao?.insert(Note("Title 3", "description 3"))
            }


        }
    }
}