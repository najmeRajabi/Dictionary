package com.example.dictionary.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {

    @Query("SELECT COUNT(*) FROM Word")
    fun countAllWords(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (vararg word: Word)

    @Delete
    fun delete(word: Word)

    @Query("SELECT * FROM Word WHERE fa LIKE :fa")
    fun findFaWord(fa : String):Word

    @Query("SELECT * FROM Word WHERE en LIKE :en")
    fun findEnWord(en : String):Word
}