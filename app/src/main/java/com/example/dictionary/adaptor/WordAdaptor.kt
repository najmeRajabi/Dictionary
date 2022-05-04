package com.example.dictionary.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.database.Word


typealias ClickHandler = (word: Word) -> Unit

class WordAdaptor(var dataSet: ArrayList<Word> ,
                  var clickHandler : ClickHandler,
                  var clickHandlerLink : ClickHandler):
                  ListAdapter<Word, WordAdaptor.ViewHolder>(WordDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordAdaptor.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_item_row, parent, false)
//        val binding:WordItemRowBinding =
//            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
//            R.layout.word_item_row, parent, false )



        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordAdaptor.ViewHolder, position: Int) {
        holder.bind(getItem(position) , clickHandler, clickHandlerLink)


    }

    object WordDiffCallback : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }
    }


    class ViewHolder( view: View): RecyclerView.ViewHolder(view){
        val txvEnglish = view.findViewById<TextView>(R.id.txv_en_row)
        val txvFarsi = view.findViewById<TextView>(R.id.txv_fa_row)
        val txvDetail = view.findViewById<TextView>(R.id.txv_more_row)
        val imvLink = view.findViewById<ImageView>(R.id.imv_link)



        fun bind (word: Word , clickHandler: ClickHandler , clickHandlerLink: ClickHandler){

            txvEnglish.text = word.en
            txvFarsi.text = word.fa
            txvDetail.setOnClickListener { clickHandler(word) }
            imvLink.setOnClickListener { clickHandlerLink(word) }
        }
    }

//    fun filterWord (word: String): LiveData<List<Word>> {
//        return
//    }
}