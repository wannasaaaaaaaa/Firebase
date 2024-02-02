package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.LoginButton.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val password = binding.LoginPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this){task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT)
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT)
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT)
            }
        }
        binding.signupText.setOnClickListener{
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
    }
}