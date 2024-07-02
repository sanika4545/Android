package com.example.music_player.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.music_player.Models.CategoryModel
import com.example.music_player.databinding.CategoryItemRecycleRowBinding
import com.example.music_player.songList


class CategoryAdapter (val categoryList: List<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: CategoryItemRecycleRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bindData(categoryModel: CategoryModel){
            binding.nameTextView.text=categoryModel.name


            Glide.with(binding.coverImageView).load(categoryModel.coverUrl)
                .apply(
                    RequestOptions().transform(RoundedCorners(70))
                )
                .into(binding.coverImageView)



            //Log.i("Song",categoryModel.song.size.toString())
            val context=binding.root.context

            binding.root.setOnClickListener{
                songList.categoryModel=categoryModel
                context.startActivity(Intent(context,songList::class.java))
            }


        }

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CategoryItemRecycleRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(categoryList[position])
    }
}