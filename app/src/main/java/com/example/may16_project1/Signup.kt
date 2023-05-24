package com.example.may16_project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.may16_project1.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.redirectLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        binding.signUp.setOnClickListener {
            val email = binding.username.text.toString().trim()
            val password = binding.password.text.toString().trim()
            val cPassword = binding.conformPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && cPassword.isNotEmpty()){
                if (password == cPassword){
                    if (password.length >= 6){
                        signUn(email, password)
                    }else{
                        Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Password doesn't matched", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Please Input all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun signUn(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task->
                if (task.isSuccessful){
                    Toast.makeText(this, "Sign-Up Successful !", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))

                }else{
                    Toast.makeText(this, "Sign-Up Failed !", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
    }
}