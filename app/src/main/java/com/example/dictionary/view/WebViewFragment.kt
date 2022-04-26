package com.example.dictionary.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.createDeviceProtectedStorageContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.fragment.navArgs
import com.example.dictionary.R
import com.example.dictionary.databinding.FragmentDetailBinding
import com.example.dictionary.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {

    val args: WebViewFragmentArgs by navArgs()
    lateinit var binding: FragmentWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val link = args.linkArg
//        val myWebView = WebView(requireContext())
//        activity?.setContentView(myWebView)
//        myWebView.loadUrl(link)
        binding.webView.loadUrl(link)
    }
}
