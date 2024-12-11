package com.example.nutrimamadaily

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class WonPage : AppCompatActivity() {

    private lateinit var circularProgressBar: CircularProgressBar
    private var progress = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.won_page) // Ganti dengan nama layout Anda jika berbeda

        // Ambil data progress dari Intent
        val totalProgress = intent.getIntExtra("progress", 0)

        // Referensi elemen UI
        circularProgressBar = findViewById(R.id.circularProgressBar)
        val tvFinalProgress = findViewById<TextView>(R.id.tvFinalProgress)

        // Set text untuk progres yang diterima
        tvFinalProgress.text = "Progres Anda: $totalProgress%"

        // Set up the button for navigation
        val btnStartPlan = findViewById<Button>(R.id.btnStartPlan)
        btnStartPlan.setOnClickListener {
            Toast.makeText(this, "Navigating to the next page...", Toast.LENGTH_SHORT).show()

            // Navigasi ke SummaryActivity atau activity lainnya
            val intent = Intent(this, SummaryActivity::class.java) // Ganti dengan nama activity yang sesuai
            startActivity(intent)
        }

        // Simulasikan progress bar dengan nilai yang didapat dari totalProgress
        simulateProgress(totalProgress)
    }

    private fun simulateProgress(totalProgress: Int) {
        Thread {
            var currentProgress = 0
            while (currentProgress < totalProgress) {
                currentProgress += 1
                handler.post {
                    circularProgressBar.setProgress(currentProgress)
                }
                Thread.sleep(50) // Simulate progress delay
            }
        }.start()
    }
}
