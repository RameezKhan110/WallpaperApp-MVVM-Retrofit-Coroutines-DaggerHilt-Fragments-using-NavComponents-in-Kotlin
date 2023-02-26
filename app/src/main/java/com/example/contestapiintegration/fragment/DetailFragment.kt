package com.example.contestapiintegration.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.contestapiintegration.R
import com.example.contestapiintegration.databinding.FragmentDetailBinding
import com.example.contestapiintegration.viewmodel.WallpaperViewModel

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    val wallpaperViewModel: WallpaperViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        wallpaperViewModel.livePageUrl.observe(viewLifecycleOwner, Observer { pageUrl ->
            binding.detailFragmentWebView.apply {
                loadUrl(pageUrl)
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
            }
        })
        return binding.root
    }

    override fun onDestroy() {
        binding == null
        super.onDestroy()
    }

}