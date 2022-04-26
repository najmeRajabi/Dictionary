package com.example.dictionary.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.dictionary.database.AppDatabase
import com.example.dictionary.database.Word
import com.example.dictionary.database.WordDao
import java.lang.Appendable

object WordRepository {

    private var db : AppDatabase? = null
    private var wordDao : WordDao? = null

    fun initDB(context: Context){
        db = AppDatabase.getAppDataBase(context)
        wordDao = db?.wordDao()

        testData()
    }

    fun getAll(): LiveData<List<Word>>? {
        return wordDao?.getAll()
    }
    fun getWord(id: Int): Word? {
        return wordDao?.getWord(id)
    }

    fun findFa (fa :String): Word? {
        return wordDao?.findFaWord(fa)
    }
    fun findEn (en :String): Word? {
        return wordDao?.findEnWord(en)
    }
    fun insert (word: Word){
        wordDao?.insert(word)
    }
    fun countWords (): LiveData<Int>? {
        return wordDao?.countAllWords()
    }
    fun delete (word: Word) {
        wordDao?.delete(word)
    }
    fun update (word: Word){
        wordDao?.updateWord(word)
    }

    fun testData(){
        wordDao?.insert(
            Word(1," درب ", "door" , "synon 1", "example 1","https://en.wikipedia.org/wiki/Door"),
            Word(2,"fa 2", "en 2" , "synon 2", "example 2"),
            Word(3,"fa 3", "en 3" , "synon 3", "example 3","https://google.com"),
            Word(4,"fa 4", "en 4" , "synon 4", "example 4"),
            Word(5,"fa 5", "en 5" , "synon 5", "example 5","https://google.com"),
            Word(6,"fa 6", "en 6" , "synon 6", "example 6"),
            Word(7,"fa 7", "en 6" , "synon 6", "example 6","https://google.com"),
            Word(8,"fa 8", "en 6" , "synon 6", "example 6"),
            Word(9,"fa 9", "en 6" , "synon 6", "example 6","https://google.com"),
            Word(10,"fa 10", "en 10" , "synon 6", "example 6")
        )
    }
}