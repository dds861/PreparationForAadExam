package com.dd.preparationforaadexam

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_table_name")
data class MyDataClass(
    @PrimaryKey(autoGenerate = true)
    val tableId: Int,
    val name: String,
    val description: String
)