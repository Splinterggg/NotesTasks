package com.app.notestodos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.notestodos.entity.Note
import com.app.notestodos.repository.Repository
import javax.inject.Inject


class NoteViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    fun insert(note: Note) {
        repository.insert(note)
    }

    fun delete(note: Note) {
        repository.delete(note)
    }

    fun deleteById(position: Int) {
        repository.deleteById(position)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getNotes(): LiveData<List<Note>> {
        return repository.getAllNotes()
    }
}
