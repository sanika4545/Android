package com.example.music_player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.music_player.Models.SongModels
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.io.path.CopyActionContext

object MyExoplayer {
    private var exoPlayer:ExoPlayer?=null
    private var currentSong:SongModels?=null
    fun getCurrentSong():SongModels?{
        return currentSong
    }
    fun getInstance():ExoPlayer?{
        return exoPlayer
    }
    fun startPlayer(context: Context,song:SongModels){
        if (exoPlayer==null)
            exoPlayer=ExoPlayer.Builder(context).build()
        if (currentSong!=song){
            updateCount()
            currentSong=song
            currentSong?.Url?.apply {
                val mediaItem=MediaItem.fromUri(this)
                exoPlayer?.setMediaItem(mediaItem)
                exoPlayer?.prepare()
                exoPlayer?.play()
            }
        }
    }
    private fun updateCount (){
        currentSong?.id?.let{id->
            FirebaseFirestore.getInstance().collection("song")
                .document(id)
                .get().addOnSuccessListener {
                    var latestCount = it.getLong("count")
                    if (latestCount == null){
                        latestCount = 1L
                    }
                    else{
                        latestCount += 1
                    }
                    FirebaseFirestore.getInstance().collection("song")
                        .document(id)
                        .update(mapOf("count" to latestCount))
                }

        }
    }

}