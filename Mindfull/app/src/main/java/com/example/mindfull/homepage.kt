package com.example.mindfull

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mindfull.databinding.ActivityHomepageBinding
import com.google.firebase.auth.FirebaseAuth

class homepage : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding
    private lateinit var backgroundSoundManager: BackgroundSoundManager
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.option.setOnClickListener {
            showPopupMenu()
            backgroundSoundManager = BackgroundSoundManager(this)
            backgroundSoundManager.startBackgroundSound()
        }


        val child = findViewById<ImageButton>(R.id.child)
        val adult = findViewById<ImageButton>(R.id.adult)


        child.setOnClickListener {

            intent = Intent(applicationContext, childmode::class.java)
            startActivity(intent)
        }
        adult.setOnClickListener {

            intent = Intent(applicationContext, adultmode::class.java)
            startActivity(intent)
        }

    }


    private fun showPopupMenu() {
        val popupMenu = PopupMenu(this, binding.option)
        val inflator = popupMenu.menuInflater
        inflator.inflate(R.menu.option, popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.logout -> {
                    logout()
                    true
                }
                R.id.AboutUs -> {
                    about()
                    true
                }
                R.id.Setting1 -> {
                    Setting1()
                    true
                }
            }
            false
        }
    }

    private fun about() {
        startActivity(Intent(this,abuot::class.java))

    }
    private fun Setting1() {
        startActivity(Intent(this,setting::class.java))

    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,login1::class.java))
        finish()
    }
}

