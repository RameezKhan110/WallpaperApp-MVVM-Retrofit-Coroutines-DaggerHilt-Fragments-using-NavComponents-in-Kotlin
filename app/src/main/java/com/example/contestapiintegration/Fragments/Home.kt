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


class Home : Fragment(), ContestAdapter.ContestInterface {

    private lateinit var binding : FragmentHomeBinding
    lateinit var sharedViewModel : SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val contest = ContestService.apiInterface

        GlobalScope.launch(Dispatchers.Main) {
            val result = contest.getContest()
            if(result!=null){
                val contestAdapter = ContestAdapter(requireContext(), result.body() as ArrayList<ContestDataItem>, this@Home)
                binding.recyclerView.adapter = contestAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

                Log.d("TAG", result.body().toString())
            }

        }
        return binding.root
    }

    override fun onDetailClicked(contests: ContestDataItem) {

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel.getUrl(contests.url)

        findNavController().navigate(R.id.action_home2_to_detail)
    }

}