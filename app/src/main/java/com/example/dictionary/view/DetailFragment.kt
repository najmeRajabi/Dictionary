package com.example.dictionary.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
    lateinit var word: Word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail, container, false)

//        val id = args.wordArg
//        binding.id = id
        binding.viewModel = vModel
        binding.lifecycleOwner = this


        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val id = args.wordArg
        binding.id = id
//        binding.word = vModel.getWord(id).value
        vModel.getWord(id).observe(viewLifecycleOwner){
            word = it
            binding.word = it
        }

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
                vModel.delete(word)
                activity?.onBackPressed()
            }
            setNegativeButton("نه") { _, _ ->

            }

        }.create().show()
    }

}