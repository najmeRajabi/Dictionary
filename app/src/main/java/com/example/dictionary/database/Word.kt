package com.example.dictionary.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey(autoGenerate = true) val id :Int,
    val fa :String,
    val en:String,
    val synonyms :String,// مترادف
    val example :String,
    val link :String? = null
) {
}