package com.example.contestapiintegration.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contestapiintegration.databinding.WallpaperItemBinding
import com.example.contestapiintegration.model.Hit
import com.example.contestapiintegration.model.WallpaperArticle

class WallpaperAdapter(
    private val context: Context,
    private val onDetailClicked: (String) -> Unit
) :
    ListAdapter<Hit, WallpaperAdapter.WallpaperViewHolder>(DiffUtil()) {

    lateinit var binding: WallpaperItemBinding

    class WallpaperViewHolder(binding: WallpaperItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        binding = WallpaperItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WallpaperViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val allWallpapers = getItem(position)
        Glide.with(context).load(allWallpapers.largeImageURL).into(binding.wallpaper)

        binding.wallpaperItem.setOnClickListener {
            onDetailClicked(allWallpapers.pageURL)
        }
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Hit, newItem: Hit
        ): Boolean {
            return oldItem == newItem
        }
    }
}