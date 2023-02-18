package com.example.contestapiintegration

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contestapiintegration.Model.ContestDataItem
import com.example.contestapiintegration.databinding.ContestItemBinding

class ContestAdapter(private val onClickListener: (String) -> Unit) :
    ListAdapter<ContestDataItem, ContestAdapter.ContestViewHolder>(DiffUtil()) {

    lateinit var binding: ContestItemBinding

    class ContestViewHolder(binding: ContestItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestViewHolder {
        binding = ContestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContestViewHolder, position: Int) {
        val allContest = getItem(position)
        binding.contestName.text = allContest.name
        binding.startTime.text = allContest.start_time
        binding.endTime.text = allContest.end_time

        binding.Item.setOnClickListener {
            onClickListener(allContest.url)
        }
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ContestDataItem>() {
        override fun areItemsTheSame(oldItem: ContestDataItem, newItem: ContestDataItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: ContestDataItem, newItem: ContestDataItem
        ): Boolean {
            return oldItem == newItem
        }
    }

}