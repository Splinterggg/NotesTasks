package com.app.notestodos.repository

import androidx.lifecycle.LiveData
import com.app.notestodos.dao.NoteDao
import com.app.notestodos.entity.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(val noteDao: NoteDao) {

    fun insert(note: Note) {
        InsertNoteCoroutine( note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesCoroutine()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return noteDao.getAllNotes()
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