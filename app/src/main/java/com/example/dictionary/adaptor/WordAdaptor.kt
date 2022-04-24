package com.example.dictionary.adaptor

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.database.Word

class WordAdaptor(val dataset: ArrayList<Word>):
                  RecyclerView.Adapter<WordAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordAdaptor.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WordAdaptor.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){}
}