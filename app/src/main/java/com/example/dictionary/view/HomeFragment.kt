package com.example.dictionary.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
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
        binding = DataBindingUtil.inflate<FragmentHomeBinding?>(layoutInflater,
            R.layout.fragment_home, container , false).apply {
                lifecycleOwner = viewLifecycleOwner
        }
        binding.viewModel = vModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        // fab for add new word.....................
        binding.fabAddWord.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addWordFragment)
        }
    }

    private fun initViews() {

        // recycler view and adapter .........................
        val recyclerViewWord = binding.recyclerWord
        val adapter = WordAdaptor(arrayListOf(),{
                word ->
            val id = word.id
            findNavController().navigate(HomeFragmentDirections.
            actionHomeFragmentToDetailFragment(id))
        }
        ){
            word ->
            if (word.link == null){
                Toast.makeText(requireContext(),
                    "لینک موجود نیست!",Toast.LENGTH_SHORT).show()
            }else
            {
                goToWebView(word.link)
            }
        }

        binding.searchEdtHome.addTextChangedListener { text ->
            vModel.searchTextChanged(binding.searchEdtHome.text.toString())
            observeWordList(adapter , recyclerViewWord)
        }

        observeWordList(adapter , recyclerViewWord)
    }

    private fun observeWordList(adapter: WordAdaptor, recyclerViewWord: RecyclerView) {
        vModel.wordList?.observe(requireActivity()){
            adapter.submitList(it)
            recyclerViewWord.adapter = adapter
        }
    }

    private fun goToWebView(link: String) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToWebViewFragment(link))
    }

}