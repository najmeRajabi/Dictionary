package com.example.dictionary.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.database.Word

class WordAdaptor(val dataSet: ArrayList<Word>):
                  RecyclerView.Adapter<WordAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordAdaptor.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_item_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordAdaptor.ViewHolder, position: Int) {
        holder.txvEnglish.text = dataSet[position].en
        holder.txvFarsi.text = dataSet[position].fa
    }

    override fun getItemCount() = dataSet.size


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txvEnglish = view.findViewById<TextView>(R.id.txv_en_row)
        val txvFarsi = view.findViewById<TextView>(R.id.txv_fa_row)
    }
}