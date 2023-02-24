package com.example.contestapiintegration.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contestapiintegration.R
import com.example.contestapiintegration.adapter.WallpaperAdapter
import com.example.contestapiintegration.api.WallpaperApi
import com.example.contestapiintegration.databinding.FragmentHomeBinding
import com.example.contestapiintegration.viewmodel.WallpaperViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    val wallpaperViewModel: WallpaperViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        wallpaperViewModel.getWallpapers()
        wallpaperViewModel.liveWallpaperList.observe(viewLifecycleOwner, Observer {
            val wallpaperAdapter = WallpaperAdapter(requireContext())
            wallpaperAdapter.submitList(it.hits)
            binding.WallpaperRecyclerView.adapter = wallpaperAdapter
            binding.WallpaperRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}