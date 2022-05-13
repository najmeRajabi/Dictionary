package com.example.dictionary.repository

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dictionary.R
import com.example.dictionary.database.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(app: Application):AndroidViewModel(app) {

    var wordList: LiveData<List<Word>>?
    var splashFlag = true
    var countWord : LiveData<Int>?

    init {
        viewModelScope.launch {
            WordRepository.initDB(app)
        }
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

    fun getWord(id: Int): MutableLiveData<Word> {
        val result= MutableLiveData<Word>()
        viewModelScope.launch {
            result.value= WordRepository.getWord(id)
        }
        return result
    }

    fun insert (word: Word){
        viewModelScope.launch (Dispatchers.IO){
            WordRepository.insert(word)
        }
    }

    private fun countWords (): LiveData<Int> {
        return WordRepository.countWords()
    }

    fun delete (word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            WordRepository.delete(word)
        }
    }

    fun update (word: Word){
        viewModelScope.launch(Dispatchers.IO) {
            WordRepository.update(word)
        }
    }

}