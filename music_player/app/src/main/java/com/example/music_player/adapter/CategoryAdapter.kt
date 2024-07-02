package com.example.music_player.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music_player.CategoryModel
import com.example.music_player.databinding.CategoryItemRecycleRowBinding

class CategoryAdapter (val categoryList: List<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: CategoryItemRecycleRowBinding):RecyclerView.ViewHolder(binding.root){
        fun bindData(categoryModel: CategoryModel){
            binding.nameTextView.text=categoryModel.name
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