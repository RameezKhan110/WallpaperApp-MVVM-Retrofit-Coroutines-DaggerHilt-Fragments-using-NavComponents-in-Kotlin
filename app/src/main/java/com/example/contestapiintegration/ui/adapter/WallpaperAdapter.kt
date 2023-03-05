package com.example.contestapiintegration.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contestapiintegration.databinding.WallpaperItemBinding
import com.example.contestapiintegration.data.model.Hit
import com.example.contestapiintegration.data.model.WallpaperArticle

class WallpaperAdapter(
    private val onDetailClicked: (String) -> Unit
) :
    ListAdapter<Hit, WallpaperAdapter.WallpaperViewHolder>(DiffUtil()) {

    class WallpaperViewHolder(binding: WallpaperItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val view = WallpaperItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WallpaperViewHolder(view)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val allWallpapers = getItem(position)
        WallpaperItemBinding.bind(holder.itemView).apply() {
            Glide.with(holder.itemView).load(allWallpapers.largeImageURL).into(wallpaper)
            wallpaperLikes.text = allWallpapers.likes.toString()
            wallpaperComments.text = allWallpapers.comments.toString()
            wallpaperViews.text = allWallpapers.views.toString()
            wallpaperItem.setOnClickListener {
                onDetailClicked(allWallpapers.pageURL)
            }
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