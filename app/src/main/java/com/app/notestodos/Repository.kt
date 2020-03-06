package com.app.notestodos

import android.app.Application
import androidx.lifecycle.LiveData
import com.app.notestodos.dao.NoteDao
import com.app.notestodos.entity.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Repository(application: Application) {
    private var noteDao: NoteDao
    private var allnotes: LiveData<List<Note>>

    init {
        val database: NoteDatabase = NoteDatabase.getInstance(
            application.applicationContext
        )
        noteDao = database.noteDao()
        allnotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        InsertNoteCoroutine( note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesCoroutine()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allnotes
    }
    fun delete(note: Note){
        deleteNoteCoroutine(note)
    }


    private fun InsertNoteCoroutine( note: Note) {
        GlobalScope.launch {
            noteDao.insert(note)
        }


    }

    private fun DeleteAllNotesCoroutine() {
        GlobalScope.launch {
            noteDao.deleteallnotes()
        }


    }
    private fun deleteNoteCoroutine(note: Note){
        GlobalScope.launch {
            noteDao.delete(note)
        }
    }
    fun deleteById(position:Int){
        deleteIdCoroutine(position)
    }
    private fun deleteIdCoroutine(position: Int){
        GlobalScope.launch {
            noteDao.deleteById(position)
        }
    }
}