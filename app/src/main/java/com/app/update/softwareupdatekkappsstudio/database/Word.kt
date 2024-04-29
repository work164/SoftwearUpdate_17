package com.app.update.softwareupdatekkappsstudio.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey @ColumnInfo(name = "name") val word: String,
    @ColumnInfo(name = "size") val size: String,
    @ColumnInfo(name = "vcode") val vcode: String,
    @ColumnInfo(name = "link") val link: String
)