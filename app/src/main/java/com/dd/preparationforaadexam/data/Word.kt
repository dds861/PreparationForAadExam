package com.dd.preparationforaadexam.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myTable")
data class Word(

        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "_id")
        val id: Long = 0,

        @ColumnInfo(name = "Word")
        val word: String
)