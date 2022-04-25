package com.example.dictionary.repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dictionary.database.Word

class WordViewModel(app: Application):AndroidViewModel(app) {


    init {
        WordRepository.initDB(app)
    }

    fun getAll(): LiveData<List<Word>>? {
        return WordRepository.getAll()
    }

    fun findFa (fa :String): Word? {
        return WordRepository.findFa(fa)
    }
    fun findEn (en :String): Word? {
        return WordRepository.findEn(en)
    }
    fun insert (word: Word){
        WordRepository.insert(word)
    }
    fun countWords (): LiveData<Int>? {
        return WordRepository.countWords()
    }
    fun delete (word: Word) {
        WordRepository.delete(word)
    }
    fun update (word: Word){
        WordRepository.update(word)
    }
}