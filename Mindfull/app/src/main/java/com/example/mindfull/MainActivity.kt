package com.example.mindfull

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mindfull.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val logBtn = findViewById<Button>(R.id.login)
        val signBtn = findViewById<Button>(R.id.signup)

        logBtn.setOnClickListener {

            intent = Intent(applicationContext, login1::class.java)
            startActivity(intent)

        }
        signBtn.setOnClickListener {
            intent = Intent(applicationContext, signup::class.java)
            startActivity(intent)
        }
    }
}