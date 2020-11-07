package com.kushal.kotlin_roombasednotesapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDataAccessObject_DAO {

    // Database Function To Access Data

    @Insert
    suspend fun addNote(note : Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
    suspend fun getAllNotes() : List<Note>

    // To Add Multiple Datas

    @Insert
    suspend fun addMultipleNotes(vararg note: Note)

}