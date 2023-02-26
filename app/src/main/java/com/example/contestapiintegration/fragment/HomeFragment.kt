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
import com.example.contestapiintegration.databinding.FragmentHomeBinding
import com.example.contestapiintegration.viewmodel.WallpaperViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val wallpaperViewModel: WallpaperViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        wallpaperViewModel.getWallpapers()
        wallpaperViewModel.liveWallpaperList.observe(viewLifecycleOwner, Observer {
            val wallpaperAdapter = WallpaperAdapter(requireContext()) { pageUrl ->
                wallpaperViewModel.getPageUrl(pageUrl)
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
            }
            wallpaperAdapter.submitList(it.hits)
            binding.WallpaperRecyclerView.adapter = wallpaperAdapter
            binding.WallpaperRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding == null
    }
}