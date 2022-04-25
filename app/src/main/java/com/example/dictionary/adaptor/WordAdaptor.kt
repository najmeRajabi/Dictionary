package com.example.dictionary.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.database.Word


typealias ClickHandler = (word: Word) -> Unit

class WordAdaptor(var dataSet: ArrayList<Word> ,
                  var clickHandler : ClickHandler):
                  ListAdapter<Word, WordAdaptor.ViewHolder>(WordDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordAdaptor.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_item_row, parent, false)



        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordAdaptor.ViewHolder, position: Int) {
        holder.bind(getItem(position) , clickHandler)


    }

    object WordDiffCallback : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txvEnglish = view.findViewById<TextView>(R.id.txv_en_row)
        val txvFarsi = view.findViewById<TextView>(R.id.txv_fa_row)



        fun bind (word: Word , clickHandler: ClickHandler){

            txvEnglish.text = word.en
            txvFarsi.text = word.fa
            txvFarsi.setOnClickListener { clickHandler(word) }
        }
    }
}