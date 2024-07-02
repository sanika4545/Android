package com.example.music_player

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.music_player.databinding.ActivityLogin1Binding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class Login1 : AppCompatActivity() {

    private lateinit var  binding: ActivityLogin1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLogin1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEdittext.text.toString()
            val password = binding.passwordEdittext.text.toString()

            if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),email)){
                binding.emailEdittext.error = "Invalid email"
                return@setOnClickListener
            }


            if(password.length < 6){
                binding.passwordEdittext.error = "Length should be 6 char"
                return@setOnClickListener
            }



            loginWithFirebase(email,password)


        }

        binding.gotoSignupBtn.setOnClickListener {
            startActivity(Intent(this,Signup::class.java))
        }
    }

    private fun loginWithFirebase(email : String, password: String){
        setInProgress(true)
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                setInProgress(false)
                Toast.makeText(applicationContext,"Login Successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@Login1,MainActivity::class.java))
                finish()
            }.addOnFailureListener {
                setInProgress(false)
                Toast.makeText(applicationContext,"Login account failed", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onResume() {
        super.onResume()
        FirebaseAuth.getInstance().currentUser?.apply {
            startActivity(Intent(this@Login1,MainActivity::class.java))
            finish()
        }
    }

    private fun setInProgress(inProgress : Boolean){
        if(inProgress){
            binding.loginBtn.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.loginBtn.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }
}