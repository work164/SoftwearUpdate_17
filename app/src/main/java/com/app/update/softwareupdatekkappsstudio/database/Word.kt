package com.app.update.softwareupdatekkappsstudio.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey(autoGenerate = true) val id:Int?=0,
    @ColumnInfo(name = "name") val word: String,
    @ColumnInfo(name = "size") val size: String,
    @ColumnInfo(name = "vcode") val vcode: String,
    @ColumnInfo(name = "pname") val pname: String
)