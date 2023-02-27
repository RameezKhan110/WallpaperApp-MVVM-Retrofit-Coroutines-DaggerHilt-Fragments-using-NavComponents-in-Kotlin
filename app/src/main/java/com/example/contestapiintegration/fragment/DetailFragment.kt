package com.example.contestapiintegration.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.contestapiintegration.R
import com.example.contestapiintegration.databinding.FragmentDetailBinding
import com.example.contestapiintegration.viewmodel.SharedViewModel

import com.example.contestapiintegration.viewmodel.WallpaperViewModel
import dagger.hilt.android.AndroidEntryPoint

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        sharedViewModel.livePageUrl.observe(viewLifecycleOwner, Observer {
            binding.detailFragmentWebView.apply {
                loadUrl(it)
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
            }
        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding == null
    }

}