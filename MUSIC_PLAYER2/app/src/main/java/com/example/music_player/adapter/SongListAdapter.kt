package com.example.music_player.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.music_player.Models.SongModels
import com.example.music_player.MyExoplayer
import com.example.music_player.PlayerActivity
import com.example.music_player.databinding.SongsItemRecyclerRowBinding
import com.google.firebase.firestore.FirebaseFirestore

class SongListAdapter(private val songIdList : List<String>) : RecyclerView.Adapter<SongListAdapter.MyViewHolder>(){
    class MyViewHolder(private val binding:SongsItemRecyclerRowBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bindData(songId:String){
            FirebaseFirestore.getInstance().collection("song")
                .document(songId).get()
                .addOnSuccessListener {
                    val song=it.toObject(SongModels::class.java)
                    song?.apply {
                        binding.songTitleTextView.text=title
                        binding.songSubtitleTextView.text=subtitle
                        Glide.with(binding.songCoverImageView).load(coverPageUrl)
                            .apply(
                                RequestOptions().transform(RoundedCorners(32))
                            )
                            .into(binding.songCoverImageView)
                        binding.root.setOnClickListener{
                            MyExoplayer.startPlayer(binding.root.context,song)
                            it.context.startActivity(Intent(it.context,PlayerActivity::class.java))
                        }

                    }
                }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val binding=SongsItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return songIdList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData((songIdList[position]))
    }
}
