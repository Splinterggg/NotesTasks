package com.app.notestodos

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.notestodos.entity.Note


class NoteViewModel (application: Application) : ViewModel() {
    private var repository: Repository = Repository(application)
    private var allNotes: LiveData<List<Note>> = repository.getAllNotes()

     fun insert(note: Note) {
        repository.insert(note)
    }

     fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }
}