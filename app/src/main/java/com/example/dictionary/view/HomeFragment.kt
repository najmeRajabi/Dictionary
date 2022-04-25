package com.example.dictionary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.dictionary.R
import com.example.dictionary.adaptor.WordAdaptor
import com.example.dictionary.database.Word
import com.example.dictionary.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        binding.fabAddWord.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addWordFragment)
        }
    }

    private fun initViews() {

        val wordList= arrayListOf(
            Word(1,"fa 1", "en 1" , "synon 1", "example 1"),
            Word(2,"fa 2", "en 2" , "synon 2", "example 2"),
            Word(3,"fa 3", "en 3" , "synon 3", "example 3"),
            Word(4,"fa 4", "en 4" , "synon 4", "example 4"),
            Word(5,"fa 5", "en 5" , "synon 5", "example 5"),
            Word(6,"fa 6", "en 6" , "synon 6", "example 6")
        )

        val recyclerViewWord = binding.recyclerWord
        val adapter = WordAdaptor(arrayListOf()){
            word ->
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        }
        adapter.submitList(wordList)
        recyclerViewWord?.adapter = adapter
//        adapter.dataSet= wordList
//        adapter.notifyDataSetChanged()
    }

}