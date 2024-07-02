package com.example.saniii

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.design)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val registerBtn = findViewById<Button>(R.id.registerBtn)

        loginBtn.setOnClickListener {
            Toast.makeText(applicationContext, "Proceed to lOGIN...", Toast.LENGTH_SHORT).show()
            intent = Intent(this,LoginPage::class.java)
            startActivity(intent)
        }
        registerBtn.setOnClickListener {

            Toast.makeText(applicationContext, "Process to register...", Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext,RegisterPage::class.java)
            startActivity(intent)
        }
    }
}