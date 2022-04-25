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
    }

    fun getAll(): LiveData<List<Word>>? {
        return wordDao?.getAll()
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
}