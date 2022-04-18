package com.example.dictionary.database

import android.app.Application

class WordApplication:Application() {

    class WordsApplication : Application() {
        // Using by lazy so the database and the repository are only created when they're needed
        // rather than when the application starts
        val database by lazy { AppDatabase.getAppDataBase(this) }
        val repository by lazy { com.example.dictionary.repository.WordRepository }
    }
}