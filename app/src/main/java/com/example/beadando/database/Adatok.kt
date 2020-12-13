package com.example.beadando.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "adatok_table")
data class Adatok {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L;


}