package com.example.nutrimamadaily

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class UtamaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utama)

        // Inisialisasi Button
        val btnHealthy = findViewById<Button>(R.id.btnHealthy)
        val btnUnHealthy = findViewById<Button>(R.id.btnUnHealthy)

        // Listener untuk tombol "Makanan Sehat"
        btnHealthy.setOnClickListener {
            val intent = Intent(this@UtamaActivity, HealthyFoodActivity::class.java)
            startActivity(intent)
        }

        // Listener untuk tombol "Makanan Tidak Sehat"
        btnUnHealthy.setOnClickListener {
            val intent = Intent(this@UtamaActivity, UnhealthyFoodActivity::class.java)
            startActivity(intent)
        }
    }
}
