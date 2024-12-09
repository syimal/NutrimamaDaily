package com.example.nutrimamadaily

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Deklarasi variabel untuk views
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var registerText: TextView
    private lateinit var forgotPassword: TextView
    private lateinit var sapaanText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi views
        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_btn)
        registerText = findViewById(R.id.register_text)
        forgotPassword = findViewById(R.id.forgot_password)
        sapaanText = findViewById(R.id.sapaan)

        // Ambil data dari intent (jika ada)
        val intent = intent
        val name = intent.getStringExtra("name")

        // Update sapaan di UI
        if (!name.isNullOrEmpty()) {
            sapaanText.text = "Hello, $name! Selamat Datang!"
        } else {
            sapaanText.text = "Hello, Selamat Datang!"
        }

        // Aksi tombol Login
        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan Password wajib diisi!", Toast.LENGTH_SHORT).show()
            } else {
                // Panggil AsyncTask untuk proses login
                LoginTask().execute(email, password)
            }
        }

        // Navigasi ke halaman Signup
        registerText.setOnClickListener {
            try {
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        // Aksi untuk lupa password
        forgotPassword.setOnClickListener {
            Toast.makeText(this, "Fitur lupa password belum diimplementasikan", Toast.LENGTH_SHORT).show()
        }
    }

    // AsyncTask untuk login
    private inner class LoginTask : AsyncTask<String, Void, Boolean>() {

        override fun doInBackground(vararg params: String?): Boolean {
            val email = params[0] ?: return false
            val password = params[1] ?: return false

            // Simulasi login, misalnya cek email dan password di server
            Thread.sleep(2000) // Simulasi operasi login yang memakan waktu

            return email == "admin@example.com" && password == "password123"
        }

        override fun onPostExecute(result: Boolean) {
            super.onPostExecute(result)
            if (result) {
                Toast.makeText(this@MainActivity, "Login sukses!", Toast.LENGTH_SHORT).show()
                // Pindah ke HomepageActivity
                val intent = Intent(this@MainActivity, HomepageActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "Login gagal! Periksa email dan password.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
