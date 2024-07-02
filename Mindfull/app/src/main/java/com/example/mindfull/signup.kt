package com.example.mindfull
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.example.mindfull.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class signup : AppCompatActivity() {

    private lateinit var  binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createAccountBtn.setOnClickListener {

            val email = binding.EmailAddress.text.toString()
            val password = binding.Password2.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()

            if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),email)){
                binding.EmailAddress.error = "Invalid email"
                return@setOnClickListener
            }

            if(password.length < 6){
                binding.Password2.error = "Length should be 6 char"
                return@setOnClickListener
            }

            if(!password.equals(confirmPassword)){
                binding.confirmPassword.error = "Password not matched"
                return@setOnClickListener
            }

            createAccountWithFirebase(email,password)
            startActivity(Intent(this,homepage::class.java))


        }

        binding.gotoLoginBtn.setOnClickListener {
            startActivity(Intent(this@signup, login1::class.java))
            finish()
        }

    }

    private fun createAccountWithFirebase(email : String, password: String){
        setInProgress(true)
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                setInProgress(false)
                Toast.makeText(applicationContext,"User created successfully",Toast.LENGTH_SHORT).show()
                finish()
            }.addOnFailureListener {
                setInProgress(false)
                Toast.makeText(applicationContext,"Create account failed",Toast.LENGTH_SHORT).show()
            }
    }


    private fun setInProgress(inProgress : Boolean){
        if(inProgress){
            binding.createAccountBtn.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.createAccountBtn.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }


}


