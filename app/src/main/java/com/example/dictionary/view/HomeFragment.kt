package com.example.dictionary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.dictionary.R
import com.example.dictionary.adaptor.WordAdaptor
import com.example.dictionary.databinding.FragmentHomeBinding
import com.example.dictionary.repository.WordViewModel

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    val vModel: WordViewModel by activityViewModels()

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

        val recyclerViewWord = binding.recyclerWord
        val adapter = WordAdaptor(arrayListOf(),{
                word ->
            val id = word.id
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id))
        }
        ){
            word ->
            if (word.link == null){
                Toast.makeText(requireContext(),"لینک موجود نیست!",Toast.LENGTH_SHORT).show()
            }else
            {
                goToWebView(word.link)
            }

        }

        vModel.wordList?.observe(requireActivity()){
            adapter.submitList(it)
        }

        recyclerViewWord?.adapter = adapter
    }

    private fun goToWebView(link: String) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToWebViewFragment(link))
    }

}