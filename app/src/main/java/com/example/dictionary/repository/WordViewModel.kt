package com.example.dictionary.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.dictionary.database.Word

class WordViewModel(app: Application):AndroidViewModel(app) {

    var wordList: LiveData<List<Word>>?
    val wordFilter = MutableLiveData<String>()
//    var wordListFilter = Transformations.map(wordFilter){
////        wordFilter.value?.let { it1 -> findWord(it1)}
////        if (findWord(it).value.isNullOrEmpty())
//        if (it.isNullOrBlank()){
//            Log.d("TAG", "blank: ${getAll().value}")
//            getAll()
//        }else {
//            Log.d("TAG", "no blank: ${findWord(it).value}")
//            findWord(it)
//        }
//    }

    val position = MutableLiveData<Int>(1)
    var countWord : LiveData<Int>?

    init {
        WordRepository.initDB(app)
        wordList = getAll()
        wordFilter.value = ""
        countWord = countWords()
    }
//    fun search (faFlag : Boolean , searchText: String): Word? {
//        return if (faFlag){
//            findFa(searchText)
//        }else {
//            findEn(searchText)
//        }
//    }
    fun findWord (word: String): LiveData<List<Word>> {
    Log.d("TAG", "findWord: $word = ${WordRepository.findWord(word).value}")
        return WordRepository.findWord(word)
    }
    fun searchTextChanged(text: String){
        wordFilter.value = text
        if (text.isNullOrBlank()){
            Log.d("TAG", "blank: ${getAll().value}")
            wordList=getAll()
        }else {
            Log.d("TAG", "no blank: ${findWord(text).value}")
            wordList=findWord(text)
        }
    }

    fun getAll(): LiveData<List<Word>> {
        return WordRepository.getAll()
    }
    fun getWord(id: Int): Word {
        return WordRepository.getWord(id)
    }

//    fun findFa (fa :String): Word? {
//        return WordRepository.findFa(fa)
//    }
//    fun findEn (en :String): Word? {
//        return WordRepository.findEn(en)
//    }
    fun insert (word: Word){
        WordRepository.insert(word)
    }
    fun countWords (): LiveData<Int> {
        return WordRepository.countWords()
    }
    fun delete (word: Word) {
//        Log.d("TAG", "delete: ${word.en}")
        WordRepository.delete(word)
    }
    fun update (word: Word){
        WordRepository.update(word)
    }
}