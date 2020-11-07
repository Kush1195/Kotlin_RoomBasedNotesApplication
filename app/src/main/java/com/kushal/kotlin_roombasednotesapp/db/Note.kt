package com.kushal.kotlin_roombasednotesapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    // Column Names
    val title: String,
    val note: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}