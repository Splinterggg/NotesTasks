package com.app.notestodos.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(

    @ColumnInfo(name = "title_column") var title: String,
    @ColumnInfo(name = "description_column") var description: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}