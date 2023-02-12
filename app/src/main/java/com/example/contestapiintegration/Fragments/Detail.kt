package com.example.contestapiintegration.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.contestapiintegration.R
import com.example.contestapiintegration.SharedViewModel
import com.example.contestapiintegration.databinding.FragmentDetailBinding

class Detail : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    lateinit var sharedViewModel : SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        sharedViewModel.url.observe(viewLifecycleOwner, Observer {
            binding.webView.apply {
                loadUrl(it)
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
            }
        })
        return binding.root
    }

}