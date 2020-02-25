package com.app.notestodos.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.app.notestodos.entity.Note

@Dao
interface NoteDao {
    @Insert
     fun insert(note: Note)
    @Delete
      fun delete(note: Note)

    @Query("DELETE FROM note_table")
      fun deleteallnotes()

    @Query("SELECT * FROM note_table")
      fun getAllNotes(): LiveData<List<Note>>
}