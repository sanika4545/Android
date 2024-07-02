package com.example.music_player

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.music_player.Models.CategoryModel
import com.example.music_player.adapter.SongListAdapter
import com.example.music_player.databinding.ActivitySongListBinding

class songList : AppCompatActivity() {
    companion object{
        lateinit var categoryModel: CategoryModel
    }
  private lateinit var binding:ActivitySongListBinding
 private lateinit var SongListAdapter:SongListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySongListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nameTextView.text= categoryModel.name
        Glide.with(binding.coverImageView).load(categoryModel.coverUrl)
            .apply(
                RequestOptions().transform(RoundedCorners(32))
            )
            .into(binding.coverImageView)
        setupSongListRecyclerView()
    }

    private fun setupSongListRecyclerView() {
        SongListAdapter = SongListAdapter(categoryModel.song)
        binding.songsListRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.songsListRecyclerView.adapter= SongListAdapter
    }

}