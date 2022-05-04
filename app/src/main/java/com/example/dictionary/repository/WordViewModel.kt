package com.example.dictionary.repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dictionary.database.Word

class WordViewModel(app: Application):AndroidViewModel(app) {

    var wordList: LiveData<List<Word>>?
    var splashFlag = true
    var countWord : LiveData<Int>?

    init {
        WordRepository.initDB(app)
        wordList = getAll()
        countWord = countWords()
    }

    private fun findWord (word: String): LiveData<List<Word>> {
        return WordRepository.findWord(word)
    }

    fun searchTextChanged(text: String){
        wordList = if (text.isNullOrBlank()){
            getAll()
        }else {
            findWord(text)
        }
    }

    private fun getAll(): LiveData<List<Word>> {
        return WordRepository.getAll()
    }

    fun getWord(id: Int): Word {
        return WordRepository.getWord(id)
    }

    fun insert (word: Word){
        WordRepository.insert(word)
    }

    private fun countWords (): LiveData<Int> {
        return WordRepository.countWords()
    }

    fun delete (word: Word) {
        WordRepository.delete(word)
    }

    fun update (word: Word){
        WordRepository.update(word)
    }
}