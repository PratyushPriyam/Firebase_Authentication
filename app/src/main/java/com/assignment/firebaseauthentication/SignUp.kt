package com.assignment.firebaseauthentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEt: EditText
    private lateinit var passET: EditText
    private lateinit var confirmPassEt: EditText
    private lateinit var button: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Find view references
        emailEt = findViewById(R.id.emailEt)
        passET = findViewById(R.id.passET)
        confirmPassEt = findViewById(R.id.confirmPassEt)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)

        // Signup Button Click Listener
        button.setOnClickListener {
            val email = emailEt.text.toString().trim()
            val password = passET.text.toString().trim()
            val confirmPassword = confirmPassEt.text.toString().trim()

            // Validate user input (empty fields, password match)
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (confirmPassword != password) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a new user with email and password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign up successful, navigate to another activity (replace with your logic)
                        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                        // Your logic after successful registration (e.g., navigate to Home activity)
                    } else {
                        Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Navigate to Login Activity on Textview Click
        textView.setOnClickListener {
            val intent = Intent(this, LogIn::class.java) // Replace with your Login activity class name
            startActivity(intent)
        }
    }
}
