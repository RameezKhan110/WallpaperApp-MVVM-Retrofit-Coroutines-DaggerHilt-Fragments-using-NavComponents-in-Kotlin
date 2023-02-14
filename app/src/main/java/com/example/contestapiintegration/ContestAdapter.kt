package com.example.contestapiintegration

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contestapiintegration.databinding.ContestItemBinding

class ContestAdapter(
    val context: Context,
    val contests: ArrayList<ContestDataItem>,
    private val onClickListener: (String) -> Unit
) :
    RecyclerView.Adapter<ContestAdapter.ContestViewHolder>() {

    lateinit var binding: ContestItemBinding

    class ContestViewHolder(binding: ContestItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestViewHolder {
        binding = ContestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContestViewHolder, position: Int) {
        val allContest = contests[position]
        binding.contestName.text = allContest.name
        binding.startTime.text = allContest.start_time
        binding.endTime.text = allContest.end_time

        binding.Item.setOnClickListener {
            onClickListener(allContest.url)
        }
    }

    override fun getItemCount(): Int {
        return contests.size
    }
}