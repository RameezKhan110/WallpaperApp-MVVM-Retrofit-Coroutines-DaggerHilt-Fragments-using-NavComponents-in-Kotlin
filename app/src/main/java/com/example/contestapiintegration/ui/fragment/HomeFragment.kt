package com.example.contestapiintegration.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contestapiintegration.R
import com.example.contestapiintegration.util.Status
import com.example.contestapiintegration.ui.adapter.WallpaperAdapter
import com.example.contestapiintegration.databinding.FragmentHomeBinding
import com.example.contestapiintegration.ui.viewmodel.SharedViewModel
import com.example.contestapiintegration.ui.viewmodel.WallpaperViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val wallpaperViewModel: WallpaperViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        wallpaperViewModel.getWallpapers()
        val wallpaperAdapter = WallpaperAdapter() { pageUrl ->
            sharedViewModel.getPageUrl(pageUrl)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
            Toast.makeText(requireContext(), pageUrl, Toast.LENGTH_SHORT).show()
        }
        binding.WallpaperRecyclerView.adapter = wallpaperAdapter
        binding.WallpaperRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        wallpaperViewModel.liveWallpaperList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.WallpaperRecyclerView.visibility = View.VISIBLE
                    wallpaperAdapter.submitList(it.data?.hits)
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.WallpaperRecyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.WallpaperRecyclerView.visibility = View.VISIBLE
                }
            }
            wallpaperAdapter.submitList(it.data?.hits)
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding == null
    }
}