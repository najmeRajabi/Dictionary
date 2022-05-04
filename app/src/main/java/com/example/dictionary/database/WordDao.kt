package com.example.dictionary.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {

    @Query(" SELECT * FROM Word ")
    fun getAll(): LiveData<List<Word>>

    @Query(" SELECT * FROM Word WHERE id = :id ")
    fun getWord (id: Int): Word

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

    @Update
    fun updateWord (word: Word)

//    @Query("SELECT * FROM Word WHERE en LIKE '%' || :word || '%' or fa LIKE '%' || :word || '%' ")

    @Query("SELECT * FROM Word WHERE en LIKE '%' || :word ")
    fun findWord(word:String): LiveData<List<Word>>


}