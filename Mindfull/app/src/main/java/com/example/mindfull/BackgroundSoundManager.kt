package com.example.mindfull

import android.content.Context
import android.media.MediaPlayer

class BackgroundSoundManager(private val context: Context) {
    private lateinit var mediaPlayer: MediaPlayer

    fun startBackgroundSound() {
        mediaPlayer = MediaPlayer.create(context, R.raw.background_sound)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    fun stopBackgroundSound() {
        mediaPlayer.stop()
    }
}