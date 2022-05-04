package com.example.dictionary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.dictionary.R
import com.example.dictionary.database.Word
import com.example.dictionary.databinding.FragmentAddWordBinding

import com.example.dictionary.repository.WordViewModel

class AddWordFragment : Fragment() {

    val args: AddWordFragmentArgs by navArgs()
    lateinit var binding: FragmentAddWordBinding
    val vModel : WordViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentAddWordBinding?>(layoutInflater,
            R.layout.fragment_add_word, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.wordId
        if (id != -1){
            initViews(id)
        }

        binding.btnSaveWord.setOnClickListener {
            if (checkField()){
                if (id != -1){
                    updateWord(id)
                }else{
                    insertWord()
                }
            }
        }
    }

    private fun insertWord() {
        optionalFiled()
        val word= Word(
            0,
            binding.edtAddPersian.text.toString(),
            binding.edtAddEnglish.text.toString(),
            binding.edtAddSynonyms.text.toString(),
            binding.edtAddExample.text.toString(),
            binding.edtAddLink.text.toString()
        )
        vModel.insert(word)
        activity?.onBackPressed()
    }

    private fun updateWord(id: Int) {
        optionalFiled()
        val word= Word(
            id,
            binding.edtAddPersian.text.toString(),
            binding.edtAddEnglish.text.toString(),
            binding.edtAddSynonyms.text.toString(),
            binding.edtAddExample.text.toString(),
            binding.edtAddLink.text.toString()
        )
        vModel.update(word)
        activity?.onBackPressed()
    }

    private fun checkField(): Boolean {
        var result = false
        when {
            binding.edtAddEnglish.text.isNullOrBlank() -> {
                binding.edtAddEnglish.error = getString(R.string.error_empty)
            }
            binding.edtAddPersian.text.isNullOrBlank() -> {
                binding.edtAddPersian.error = getString(R.string.error_empty)
            }
            else -> {
                result = true
            }
        }
        return result
    }

    private fun initViews(id: Int) {
        val word = vModel.getWord(id)
        binding.edtAddEnglish.setText(word?.en)
        binding.edtAddPersian.setText(word?.fa)
        binding.edtAddExample.setText(word?.example)
        binding.edtAddSynonyms.setText(word?.synonyms)
    }

    fun optionalFiled(){
        if (binding.edtAddSynonyms.text.isNullOrBlank()){
            binding.edtAddSynonyms.setText(getString(R.string.empty))
        }
        if (binding.edtAddExample.text.isNullOrBlank()){
            binding.edtAddExample.setText(getString(R.string.empty))
        }
        if (binding.edtAddLink.text.isNullOrBlank()){
            binding.edtAddLink.setText(getString(R.string.empty))
        }
    }
}