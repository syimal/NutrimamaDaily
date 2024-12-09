package com.example.nutrimamadaily

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {
    var signupName: EditText? = null
    var signupEmail: EditText? = null
    var signupUsername: EditText? = null
    var signupPassword: EditText? = null
    var loginRedirectText: TextView? = null
    var signupButton: Button? = null
    var database: FirebaseDatabase? = null
    var reference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize the views
        signupName = findViewById<EditText>(R.id.signup_name)
        signupEmail = findViewById<EditText>(R.id.signup_email)
        signupUsername = findViewById<EditText>(R.id.signup_username)
        signupPassword = findViewById<EditText>(R.id.signup_password)
        loginRedirectText = findViewById<TextView>(R.id.loginRedirectText)
        signupButton = findViewById<Button>(R.id.signup_button)

        // Set up the signup button
        signupButton?.setOnClickListener {
            // Get input data
            val name = signupName?.text.toString()
            val email = signupEmail?.text.toString()
            val username = signupUsername?.text.toString()
            val password = signupPassword?.text.toString()

            // Check if fields are not empty
            if (name.isNotEmpty() && email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                database = FirebaseDatabase.getInstance()
                reference = database?.getReference("users")

                // Create a HelperClass object
                val helperClass = HelperClass(name, email, username, password)

                // Save the user data in Firebase
                reference?.child(username)?.setValue(helperClass)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Signup failed. Try again.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show()
            }
        }


        // Redirect to login activity
        loginRedirectText?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}