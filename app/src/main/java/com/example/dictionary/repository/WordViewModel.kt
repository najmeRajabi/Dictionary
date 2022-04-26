package com.example.dictionary.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dictionary.database.Word

class WordViewModel(app: Application):AndroidViewModel(app) {

    var wordList: LiveData<List<Word>>?
    val position = MutableLiveData<Int>(1)
    var countWord : LiveData<Int>?

    init {
        WordRepository.initDB(app)
        wordList = getAll()
        countWord = countWords()
    }

    fun getAll(): LiveData<List<Word>>? {
        return WordRepository.getAll()
    }
    fun getWord(id: Int): Word? {
        return WordRepository.getWord(id)
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
        Log.d("TAG", "delete: ${word.en}")
        WordRepository.delete(word)
    }
    fun update (word: Word){
        WordRepository.update(word)
    }
}