package com.example.contestapiintegration.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contestapiintegration.R
import com.example.contestapiintegration.adapter.WallpaperAdapter
import com.example.contestapiintegration.databinding.FragmentHomeBinding
import com.example.contestapiintegration.viewmodel.SharedViewModel
import com.example.contestapiintegration.viewmodel.WallpaperViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val wallpaperViewModel: WallpaperViewModel by viewModels()
    lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        wallpaperViewModel.getWallpapers()
        val wallpaperAdapter = WallpaperAdapter() { pageUrl ->
            sharedViewModel.getPageUrl(pageUrl)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
            Toast.makeText(requireContext(), pageUrl, Toast.LENGTH_SHORT).show()
        }
        binding.WallpaperRecyclerView.adapter = wallpaperAdapter
        binding.WallpaperRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        wallpaperViewModel.liveWallpaperList.observe(viewLifecycleOwner, Observer {
            wallpaperAdapter.submitList(it.hits)
        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding == null
    }
}