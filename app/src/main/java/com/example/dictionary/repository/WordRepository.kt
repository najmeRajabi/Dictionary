package com.example.dictionary.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.dictionary.database.AppDatabase
import com.example.dictionary.database.Word
import com.example.dictionary.database.WordDao

object WordRepository {

    private var db : AppDatabase? = null
    private lateinit var wordDao : WordDao


    suspend fun initDB(context: Context){
        db = AppDatabase.getAppDataBase(context)
        wordDao = AppDatabase.getAppDataBase(context).wordDao()

        testData()
    }

    fun getAll(): LiveData<List<Word>> {
        return wordDao.getAll()
    }
    suspend fun getWord(id: Int): Word {
        return wordDao.getWord(id)
    }
    fun findWord (word: String): LiveData<List<Word>> {
        return wordDao.findWord(word)
    }
    suspend fun insert (word: Word){
        wordDao.insert(word)
    }
    fun countWords (): LiveData<Int> {
        return wordDao.countAllWords()
    }
    suspend fun delete (word: Word) {
        wordDao.delete(word)
    }
    suspend fun update (word: Word){
        wordDao.updateWord(word)
    }

    private suspend fun testData(){
        wordDao.insert(
            Word(1," درب ", "door" , "synon 1", "example 1","https://en.wikipedia.org/wiki/Door",false),
            Word(2,"fa 2", "en 2" , "synon 2", "example 2",null,false),
            Word(3,"fa 3", "en 3" , "synon 3", "example 3","https://google.com",false),
            Word(4,"fa 4", "en 4" , "synon 4", "example 4",null,false),
            Word(5,"fa 5", "en 5" , "synon 5", "example 5","https://google.com",false),
            Word(6,"fa 6", "en 6" , "synon 6", "example 6",null,false),
            Word(7,"fa 7", "en 6" , "synon 6", "example 6","https://google.com",true),
            Word(8,"fa 8", "en 6" , "synon 6", "example 6",null,false),
            Word(9,"fa 9", "en 6" , "synon 6", "example 6","https://google.com",false),
            Word(10,"fa 10", "en 10" , "synon 6", "example 6",null,false)
        )
    }
}