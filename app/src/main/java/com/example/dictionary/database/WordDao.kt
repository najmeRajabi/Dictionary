package com.example.dictionary.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {

    @Query(" SELECT * FROM Word ")
    fun getAll(): LiveData<List<Word>>

    @Query(" SELECT * FROM Word WHERE id = :id ")
    suspend fun getWord (id: Int): Word

    @Query("SELECT COUNT(*) FROM Word")
    fun countAllWords(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (vararg word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Update
    suspend fun updateWord (word: Word)

    @Query("SELECT * FROM Word WHERE en LIKE '%' || :word || '%' or fa LIKE '%' || :word || '%' ")
    fun findWord(word:String): LiveData<List<Word>>


}