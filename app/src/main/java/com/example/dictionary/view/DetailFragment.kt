package com.example.dictionary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dictionary.R
import com.example.dictionary.database.Word
import com.example.dictionary.databinding.FragmentDetailBinding
import com.example.dictionary.repository.WordViewModel

class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()
    lateinit var binding: FragmentDetailBinding
    val vModel : WordViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val id = args.wordArg
        initView( id )

        binding.btnDeleteWord.setOnClickListener {
            deleteWord(id)
        }

        binding.btnEditWord.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToAddWordFragment(id))
        }

    }

    private fun deleteWord(id: Int) {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle("مطمئن هستید؟؟")
            setMessage("تمامی اطلاعات این واژه پاک خواهد شد!")
            setPositiveButton("مطمئنم") { _, _ ->
//                Toast.makeText(requireContext(),"deleted", Toast.LENGTH_SHORT).show()
                vModel.delete(vModel.getWord(id)!!)
                activity?.onBackPressed()
            }
            setNegativeButton("نه") { _, _ ->
                // dismiss
            }

        }.create().show()
    }

    private fun initView( id: Int) {
        val word  = vModel.getWord(id)!!
            binding.txvEn.text = word.en
            binding.txvFa.text = word.fa
            binding.txvExample.text = word.example
            binding.txvSynonyms.text = word.synonyms

    }

}