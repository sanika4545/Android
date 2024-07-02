package com.example.mindfull

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity


class setting : AppCompatActivity() {

    private lateinit var backgroundSoundManager: BackgroundSoundManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        backgroundSoundManager = BackgroundSoundManager(this)

        val switchBackgroundSound = findViewById<Switch>(R.id.switch_background_sound)
        switchBackgroundSound.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                backgroundSoundManager.startBackgroundSound()
            } else {
                backgroundSoundManager.stopBackgroundSound()
            }
        }
    }
}