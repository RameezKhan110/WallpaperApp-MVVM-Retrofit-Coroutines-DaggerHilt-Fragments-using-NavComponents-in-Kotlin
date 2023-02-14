package com.example.contestapiintegration.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contestapiintegration.*
import com.example.contestapiintegration.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val contest = ContestService.apiInterface
        GlobalScope.launch(Dispatchers.Main) {
            val result = contest.getContest()
            try {
                val contestAdapter = ContestAdapter(
                    requireContext(),
                    result.body() as ArrayList<ContestDataItem>) { url ->
                    sharedViewModel.getUrl(url)
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
                }
                binding.recyclerView.adapter = contestAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                Log.d("TAG", result.body().toString())
            } catch (e: Exception) {
                Log.d("TAG", "Error in Fetching Data", e)
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}