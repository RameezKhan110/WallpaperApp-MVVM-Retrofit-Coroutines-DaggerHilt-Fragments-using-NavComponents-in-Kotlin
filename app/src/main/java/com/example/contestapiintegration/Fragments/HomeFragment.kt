package com.example.contestapiintegration.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contestapiintegration.*
import com.example.contestapiintegration.Repository.ContestRepository
import com.example.contestapiintegration.ViewModel.ContestViewModel
import com.example.contestapiintegration.ViewModel.ContestViewModelFactory
import com.example.contestapiintegration.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    var result = ArrayList<ContestDataItem>()
    lateinit var sharedViewModel: SharedViewModel
    lateinit var contestViewModel: ContestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val contestRepo = ContestRepository()
        contestViewModel = ViewModelProvider(
            this,
            ContestViewModelFactory(contestRepo)
        ).get(ContestViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        contestViewModel.getAllContest()
        contestViewModel.contestList.observe(viewLifecycleOwner, Observer {
            val contestAdapter = ContestAdapter(
                requireContext(), it as ArrayList<ContestDataItem>
            ) { url ->
                sharedViewModel.getUrl(url)
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
            }
            binding.recyclerView.adapter = contestAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            Log.d("TAG", it.toString())
        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}