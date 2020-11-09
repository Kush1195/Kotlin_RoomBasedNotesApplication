package com.kushal.kotlin_roombasednotesapp.db

import androidx.room.*

@Dao
interface NotesDataAccessObject_DAO {

    // Database Function To Access Data

    @Insert
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
    suspend fun getAllNotes(): List<Note>

    // To Add Multiple Data
    @Insert
    suspend fun addMultipleNotes(vararg note: Note)

    // To Update Data
    @Update
    suspend fun updateNote(note: Note)

    // To Delete Data
    @Delete
    suspend fun deleteNote(note: Note)
}